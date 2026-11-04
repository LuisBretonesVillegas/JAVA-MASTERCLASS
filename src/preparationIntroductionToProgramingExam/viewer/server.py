"""Servidor local del visor de apuntes.

Sirve la carpeta `src/` del proyecto y expone `/api/tree` con la lista de
archivos (.md, .java y enunciados sin extensión) y su fecha de modificación,
para que la página se actualice sola al guardar desde el IDE.

Uso:  python src/preparationIntroductionToProgramingExam/viewer/server.py [puerto]
"""
import http.server
import json
import os
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]          # .../JAVA-UDEMY/src
VIEWER = "/preparationIntroductionToProgramingExam/viewer/"
SKIP_DIRS = {"viewer", "out", "target", ".idea"}   # los PDF nunca se sirven: solo .md/.java


def is_content(p: Path) -> bool:
    if p.suffix.lower() in (".md", ".java"):
        return True
    return p.suffix == "" and p.name.lower().startswith("statement")


def tree(full=False):
    files = []
    for dirpath, dirnames, filenames in os.walk(ROOT):
        dirnames[:] = sorted(d for d in dirnames if d not in SKIP_DIRS and not d.startswith("."))
        for name in sorted(filenames):
            p = Path(dirpath) / name
            if is_content(p):
                st = p.stat()
                item = {
                    "path": p.relative_to(ROOT).as_posix(),
                    "mtime": st.st_mtime_ns,
                    "size": st.st_size,
                }
                if full:  # primera carga: todo el contenido en una sola petición
                    item["text"] = p.read_text(encoding="utf-8", errors="replace")
                files.append(item)
    return files


class Handler(http.server.SimpleHTTPRequestHandler):
    def __init__(self, *a, **kw):
        super().__init__(*a, directory=str(ROOT), **kw)

    def end_headers(self):
        self.send_header("Cache-Control", "no-store")
        super().end_headers()

    def do_GET(self):
        if self.path in ("/", "/index.html"):
            self.send_response(302)
            self.send_header("Location", VIEWER)
            self.end_headers()
            return
        if self.path.startswith("/api/tree"):
            body = json.dumps(tree(full="full=1" in self.path)).encode("utf-8")
            self.send_response(200)
            self.send_header("Content-Type", "application/json; charset=utf-8")
            self.send_header("Content-Length", str(len(body)))
            self.end_headers()
            self.wfile.write(body)
            return
        if self.path.lower().split("?")[0].endswith(".pdf"):
            self.send_error(404)
            return
        super().do_GET()

    def guess_type(self, path):
        p = str(path)
        if p.endswith(".md") or p.endswith(".java") or Path(p).suffix == "":
            return "text/plain; charset=utf-8"
        return super().guess_type(path)

    def log_message(self, fmt, *args):
        if "/api/tree" not in (args[0] if args else ""):
            super().log_message(fmt, *args)


if __name__ == "__main__":
    PORT = int(sys.argv[1]) if len(sys.argv) > 1 else 8765
    with http.server.ThreadingHTTPServer(("127.0.0.1", PORT), Handler) as httpd:
        print(f"Visor de apuntes en http://localhost:{PORT}{VIEWER}  (raíz: {ROOT})", flush=True)
        httpd.serve_forever()
