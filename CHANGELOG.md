# LuckyBlock TACZ 1.0.2

**Minecraft 1.20.1 · Forge · 需要 [Timeless and Classics Zero (TaCZ)](https://www.curseforge.com/minecraft/mc-mods/timeless-and-classics-zero)**

---

## 中文

### ✨ 新增

- **幸运方块** `luckyblocktacz:lucky_block_tacz`——挖开后掉落一件随机物品。
- **枪械 + 配套弹药成对掉落**：从 70 把 TaCZ 枪械中随机抽取一把，同时掉出它对应的弹药（60 发），枪与弹必定属于同一口径，不会再出现"有枪没弹"。
- **近战与投掷物**：安装了 TaCZ 绿葡萄战术装备（`lrtactical`）时，掉落池额外包含 11 件近战武器与 5 件投掷物；未安装时自动跳过，不会报错。
- **地表结构生成**：幸运方块会以地表结构的形式自然生成在世界中（`luckyblocktacz:lucky_block_tacz_surface`，约每 8 个区块一组，随机 8 次尝试）。
- **地下矿脉生成**：方块也会像矿石一样生成在 Y 60–120 之间。
- **箱子战利品**：各类地牢、神庙、矿道等约 24 种箱子有 10% 几率开出幸运方块。
- **合成配方**：9 个泥土即可合成 1 个幸运方块。

### 🐛 修复

- 修复枪械与弹药**无法同时掉落**的问题——此前随机逻辑只会抽取一条记录，永远拿不到配套弹药。
- 修复挖掉幸运方块后**还会掉落方块本体**的问题，现在只掉落战利品。
- 修复箱子战利品修改器**完全未生效**的问题（缺少 Forge 全局战利品修改器的注册清单文件）。
- 修正战利品条件类型 `minecraft:alternative` → `minecraft:any_of`。

### 📄 其他

- 授权协议改为 **MIT**（此前为 All Rights Reserved），源代码公开于 [GitHub](https://github.com/SYSTEM-RAM-OP/LuckyBlockTACZ)。
- README 中与 MIT 冲突的再分发限制已移除。

### ⚠️ 注意

- 必须安装 **TaCZ** 才能掉落枪械与弹药。
- **旧世界**中已生成的地形不会补生成结构；请在新创建的区块或新世界中使用，或使用 `/place structure luckyblocktacz:lucky_block_tacz_surface` 手动放置。

---

## English

### ✨ Added

- **Lucky Block** `luckyblocktacz:lucky_block_tacz` — break it to receive a random item.
- **Paired gun + ammo drops**: picks one of 70 TaCZ guns at random and drops its matching ammunition (60 rounds) at the same time. The gun and its ammo always share the same caliber, so you never get a gun without bullets.
- **Melee & throwables**: with TaCZ Green Grape Tactical Equipment (`lrtactical`) installed, the loot pool also contains 11 melee weapons and 5 throwables. Skipped automatically when absent — no errors.
- **Surface structure generation**: the Lucky Block generates naturally as a surface structure (`luckyblocktacz:lucky_block_tacz_surface`, roughly one attempt set per 8 chunks, 8 tries each).
- **Underground ore generation**: the block also generates like an ore between Y 60 and Y 120.
- **Chest loot**: roughly 24 dungeon, temple and mineshaft loot tables have a 10% chance to contain a Lucky Block.
- **Crafting recipe**: 9 dirt blocks craft 1 Lucky Block.

### 🐛 Fixed

- Fixed guns and ammo **never dropping together** — the previous random logic only drew a single entry, so the matching ammo was never given.
- Fixed the block **dropping itself** in addition to its loot.
- Fixed the chest loot modifier **never applying** (missing the Forge global loot modifier registry file).
- Corrected the loot condition type `minecraft:alternative` → `minecraft:any_of`.

### 📄 Other

- Relicensed under **MIT** (previously All Rights Reserved). Source code is available on [GitHub](https://github.com/SYSTEM-RAM-OP/LuckyBlockTACZ).
- Removed redistribution restrictions from the README that conflicted with MIT.

### ⚠️ Notes

- **TaCZ** is required for gun and ammo drops.
- Already-generated terrain in **existing worlds** will not retroactively gain the structure. Use a new world or newly generated chunks, or place it manually with `/place structure luckyblocktacz:lucky_block_tacz_surface`.
