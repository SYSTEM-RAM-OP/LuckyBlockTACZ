import json
import os
import re
import sys
import zipfile

TACZ_GUN_PREFIX = "assets/tacz/custom/tacz_default_gun/data/tacz/data/guns/"
TACZ_ATTACHMENT_PREFIX = "assets/tacz/custom/tacz_default_gun/data/tacz/data/attachments/"


def clean(text):
    text = re.sub(r"//[^\n]*", "", text)
    text = re.sub(r"/\*.*?\*/", "", text, flags=re.DOTALL)
    text = re.sub(r",\s*([}\]])", r"\1", text)
    return text


def find_jar(mods_dir, needle):
    for name in sorted(os.listdir(mods_dir)):
        if name.endswith(".jar") and needle in name.lower():
            return os.path.join(mods_dir, name)
    raise SystemExit("jar not found for: " + needle)


def tacz_data(jar_path):
    guns = {}
    attachments = []
    with zipfile.ZipFile(jar_path) as z:
        for name in z.namelist():
            if not name.endswith("_data.json"):
                continue
            if name.startswith(TACZ_GUN_PREFIX):
                gun_id = name[len(TACZ_GUN_PREFIX):-len("_data.json")]
                try:
                    data = json.loads(clean(z.read(name).decode("utf-8")))
                except ValueError:
                    continue
                ammo = data.get("ammo")
                if ammo:
                    guns[gun_id] = ammo
            elif name.startswith(TACZ_ATTACHMENT_PREFIX):
                attachments.append(name[len(TACZ_ATTACHMENT_PREFIX):-len("_data.json")])
    return guns, sorted(attachments)


def index_ids(jar_path, mod_id):
    result = {"consumable": [], "melee": [], "throwable": []}
    prefix = "data/%s/index/" % mod_id
    with zipfile.ZipFile(jar_path) as z:
        for name in z.namelist():
            if not name.startswith(prefix) or not name.endswith(".json"):
                continue
            rel = name[len(prefix):-len(".json")]
            if "/" not in rel:
                continue
            kind, ident = rel.split("/", 1)
            if kind in result:
                result[kind].append(ident)
    return {k: sorted(v) for k, v in result.items()}


def main():
    mods_dir = sys.argv[1] if len(sys.argv) > 1 else os.environ.get("LUCKYBLOCK_MODS_DIR", "")
    if not mods_dir or not os.path.isdir(mods_dir):
        raise SystemExit("mods directory not found: " + repr(mods_dir))

    guns, attachments = tacz_data(find_jar(mods_dir, "tacz-forge"))
    lrtactical = index_ids(find_jar(mods_dir, "lrtactical-forge"), "lrtactical")
    delta_wt = index_ids(find_jar(mods_dir, "deltaforce"), "delta_wt")

    ref = {
        "guns": guns,
        "attachments": attachments,
        "lrtactical": lrtactical,
        "delta_wt_melee": delta_wt["melee"],
    }
    out = os.path.join(os.path.dirname(os.path.abspath(__file__)), "reference-ids.json")
    with open(out, "w", encoding="utf-8") as f:
        json.dump(ref, f, indent=2, ensure_ascii=False)
        f.write("\n")
    print("guns=%d attachments=%d consumables=%d melee=%d throwables=%d delta_melee=%d"
          % (len(guns), len(attachments), len(lrtactical["consumable"]),
             len(lrtactical["melee"]), len(lrtactical["throwable"]), len(delta_wt["melee"])))
    print("wrote " + out)


if __name__ == "__main__":
    main()
