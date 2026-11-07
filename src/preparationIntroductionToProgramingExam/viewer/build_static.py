"""Genera una versión estática del visor (un solo index.html con todos los apuntes dentro).

Sirve para publicarlo en study.luisbretones.dev (GitHub Pages), donde no hay servidor Python.

Uso:
    python src/preparationIntroductionToProgramingExam/viewer/build_static.py ../study/IP/index.html

Solo incluye .md, .java y enunciados; nunca PDFs.
"""
import json
import os
import sys
import time
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import server  # reutiliza la misma lista de archivos que el servidor local

MARK = "<script>\n/* ================= Modelo"


def main():
    if len(sys.argv) != 2:
        sys.exit("Uso: build_static.py <ruta de salida/index.html>")
    out = Path(sys.argv[1]).resolve()
    html = (Path(__file__).parent / "index.html").read_text(encoding="utf-8")
    if MARK not in html:
        sys.exit("No encuentro el punto de inserción en index.html")

    files = server.tree(full=True)
    assert not any(f["path"].lower().endswith(".pdf") for f in files)
    # BUILD_TIME_MS lo fija el workflow nocturno de study (fecha del último commit),
    # así la salida solo cambia cuando cambian los apuntes
    built = int(os.environ.get("BUILD_TIME_MS") or time.time() * 1000)
    data = {"built": built, "files": files}
    # "</" escapado para que ningún texto pueda cerrar el <script>
    payload = json.dumps(data, ensure_ascii=False).replace("</", "<\\/")
    html = html.replace("<title>Apuntes IP Java</title>", "<title>Apuntes IP Java | Luis Bretones</title>\n<meta name=\"robots\" content=\"noindex\">\n<link rel=\"icon\" type=\"image/svg+xml\" href=\"/favicon.svg\">")
    html = html.replace(MARK, f"<script>window.EMBEDDED = {payload};</script>\n{MARK}", 1)

    out.parent.mkdir(parents=True, exist_ok=True)
    out.write_text(html, encoding="utf-8", newline="\n")
    print(f"{out}  ({len(files)} archivos, {out.stat().st_size // 1024} KB)")


if __name__ == "__main__":
    main()
