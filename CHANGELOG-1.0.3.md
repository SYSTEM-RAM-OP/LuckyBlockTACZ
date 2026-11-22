## LuckyBlock TACZ 1.0.3

**Minecraft 1.20.1 · Forge**

### Overview

LuckyBlock TACZ is a lightweight lucky-block add-on for TaCZ. It does **not** require the original Lucky Block mod. Break the block to roll a random TaCZ firearm together with perfectly matched ammo, ready to fire, no mismatches.

### Added

- **Individual attachment drops**: 117 TaCZ attachments (scopes, magazines, muzzles, grips, lasers) can now drop directly from the lucky block.
- **Gun + ammo + attachment bundles**: 50 new drop sets that give a gun, 90 rounds of matching ammo, and a scope/attachment suited to that gun in a single break.
- **Consumables**: with Green Grape Tactical Gear (`lrtactical`) installed, 15 consumables (bandages, painkillers, grenades, etc.) also drop.

### Fixed

- **Delta WT melee weapons dropping as empty shells**: the 11 Delta melee knives previously only checked `lrtactical`, so players without `delta_wt` received unusable items. They now require both mods and are excluded from the drop pool when `delta_wt` is missing.

### Carried over from 1.0.2

70 guns with perfectly matched ammo, surface structure generation, underground ore generation (Y 60–120), ~24 chest loot tables at 10% chance, and a 9-dirt crafting recipe.

### Requirements

- **Required**: TaCZ (`tacz`) 1.2.0
- **Optional**: Green Grape Tactical Gear (`lrtactical`)
- **Optional**: Delta WT (`delta_wt`)

### Update

Replace the old jar in your `mods` folder with `LuckyBlockTACZ-1.0.3.jar` and restart the game.

### Maven (for developers)

```gradle
repositories { maven { url "https://jitpack.io" } }
dependencies { implementation "com.github.SYSTEM-RAM-OP:LuckyBlockTACZ:1.0.3" }
```

---

## LuckyBlock TACZ 1.0.3（中文）

**Minecraft 1.20.1 · Forge**

### 模组简介

LuckyBlock TACZ 是一个轻量级的 TaCZ 幸运方块模组，**不依赖原版 Lucky Block 模组**。挖开幸运方块，随机获得一把 TaCZ 枪械，并同时掉落与之口径完全匹配的弹药，开箱即射，不会枪弹错配。

### 1.0.3 新增

- **枪械附件单件掉落**：新增 117 种 TaCZ 附件（瞄具、弹匣、枪口、握把、镭射等），挖开方块有概率直接开出附件。
- **「枪 + 弹药 + 附件」三件套**：新增 50 组配套掉落，一次开出枪械、90 发匹配弹药和该枪型适配的瞄具/配件。
- **消耗品掉落**：安装绿葡萄战术装备（`lrtactical`）后，额外掉落 15 种消耗品（绷带、止痛药、手雷等）。

### 1.0.3 修复

- **修复 Delta WT 近战刀掉出「空刀」坏物品**：11 把三角洲近战刀此前只校验 `lrtactical`，未安装 `delta_wt` 时会掉出无法使用的空壳。现在同时校验两个模组，未安装 `delta_wt` 时该组不再进入掉落池。

### 沿用 1.0.2 的能力

70 把枪械 + 配套弹药成对掉落、地表结构生成、地下矿脉生成（Y 60–120）、约 24 种箱子 10% 概率开出、9 个泥土合成配方。

### 前置依赖

- **必须**：TaCZ（`tacz`），版本 1.2.0
- **可选**：绿葡萄战术装备（`lrtactical`）
- **可选**：Delta WT（`delta_wt`）

### 更新方法

用本页的 `LuckyBlockTACZ-1.0.3.jar` 替换 `mods` 目录中的旧版本，重启游戏。

### 开发参考（Maven）

```gradle
repositories { maven { url "https://jitpack.io" } }
dependencies { implementation "com.github.SYSTEM-RAM-OP:LuckyBlockTACZ:1.0.3" }
```