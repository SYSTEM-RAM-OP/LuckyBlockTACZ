import gzip
import struct
import sys


def _str(s):
    b = s.encode("utf-8")
    return struct.pack(">H", len(b)) + b


def _tag(t, name, payload):
    return bytes([t]) + _str(name) + payload


def _int(v):
    return struct.pack(">i", v)


def _list(elem_type, items):
    return struct.pack(">bi", elem_type, len(items)) + b"".join(items)


def _compound(named):
    return b"".join(_tag(*x) for x in named) + b"\x00"


def _int_list(vals):
    return _list(3, [_int(v) for v in vals])


def _compound_tag(name, named):
    return _tag(10, name, _compound(named))


def build(size, palette, blocks):
    root = []
    root.append((3, "DataVersion", _int(3465)))
    root.append((9, "size", _list(3, [_int(v) for v in size])))
    root.append((9, "palette", _list(10, [
        _compound([(8, "Name", _str(n))]) for n in palette
    ])))
    entries = []
    for pos, state, nbt in blocks:
        named = [(9, "pos", _int_list(list(pos))), (3, "state", _int(state))]
        if nbt:
            named.append((10, "nbt", _compound(nbt)))
        entries.append(_compound(named))
    root.append((9, "blocks", _list(10, entries)))
    root.append((9, "entities", _list(10, [])))
    return _tag(10, "", _compound(root))


def main(out_path):
    palette = ["minecraft:stone_bricks", "minecraft:chest",
               "luckyblocktacz:lucky_block_tacz"]
    stone, chest, lucky = 0, 1, 2

    blocks = []
    for x in range(5):
        for z in range(5):
            blocks.append(((x, 0, z), stone, None))

    for cx, cz in ((0, 0), (4, 0), (0, 4), (4, 4)):
        blocks.append(((cx, 1, cz), stone, None))
        blocks.append(((cx, 2, cz), stone, None))

    blocks.append(((2, 1, 2), lucky, None))
    blocks.append(((3, 1, 2), chest,
                   [(8, "LootTable", _str("minecraft:chests/simple_dungeon"))]))

    data = gzip.compress(build((5, 3, 5), palette, blocks))
    with open(out_path, "wb") as f:
        f.write(data)
    print("wrote %s (%d bytes, %d blocks)" % (out_path, len(data), len(blocks)))


if __name__ == "__main__":
    main(sys.argv[1])
