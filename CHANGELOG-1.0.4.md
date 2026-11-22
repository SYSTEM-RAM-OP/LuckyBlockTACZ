## LuckyBlock TACZ 1.0.4

**Minecraft 1.20.1 · Forge**

### Overview

LuckyBlock TACZ is a lightweight lucky-block add-on for TaCZ. It does **not** require the original Lucky Block mod. Break the block to roll a random TaCZ firearm together with perfectly matched ammo, ready to fire, no mismatches.

### Fixed

- **Consumables, melee and throwables were dropping as nothing.** The mod registered them as items like `lrtactical:ai2` or `lrtactical:karambit`, but Green Grape Tactical Gear only registers five generic items (`consumable`, `melee`, `throwable`, `flash_shield`, `detonator`) and distinguishes the actual content through NBT. Every consumable, melee and throwable entry therefore produced an empty drop. They now use the correct item plus `ConsumableId` / `MeleeWeaponId` / `ThrowableId` tags.
- **One attachment in the AK-74M bundle did not exist.** `scope_okp7` is not a valid attachment id (the real one is `sight_okp7`), so that bundle always dropped a gun and ammo without the attachment. Fixed.

### Added

- **Simplified Chinese localization.** The block and its creative tab now show as 幸运方块 TACZ in a Chinese client.
- **Mod metadata.** `issueTrackerURL`, a 128×128 `logoFile`, and `displayTest="IGNORE_ALL_VERSION"` are now declared in `mods.toml`, so version mismatches with TaCZ no longer show a red warning.
- **Loot id validation.** A `validateLootIds` Gradle task now cross-checks every drop id against a generated reference snapshot (`tools/reference-ids.json`) and runs as part of `check`. A typo in a gun, ammo, attachment or consumable id now fails the build instead of silently dropping nothing.

### Improved

- **Reworked surface shrine.** The generated structure is now a 5×3×5 stone-brick platform with four corner pillars, the lucky block in the centre and a dungeon loot chest beside it, instead of a single floating block.

### Carried over from 1.0.3

70 guns with matched ammo, 117 individual attachments, 50 gun + ammo + attachment bundles, consumables with Green Grape Tactical Gear, the 11 Delta WT knives, underground ore generation (Y 60–120), ~24 chest loot tables at 10% chance, and a 9-dirt crafting recipe.

### Requirements

- **Required**: TaCZ (`tacz`) 1.2.0
- **Optional**: Green Grape Tactical Gear (`lrtactical`)
- **Optional**: Delta WT (`delta_wt`)

### Update

Replace the old jar in your `mods` folder with `LuckyBlockTACZ-1.0.4.jar` and restart the game.

### Maven (for developers)

```gradle
repositories { maven { url "https://jitpack.io" } }
dependencies { implementation "com.github.SYSTEM-RAM-OP:LuckyBlockTACZ:1.0.4" }
```

---

## LuckyBlock TACZ 1.0.4（中文）

**Minecraft 1.20.1 · Forge**

### 模组简介

LuckyBlock TACZ 是一个轻量级的 TaCZ 幸运方块模组，**不依赖原版 Lucky Block 模组**。挖开幸运方块，随机获得一把 TaCZ 枪械，并同时掉落与之口径完全匹配的弹药，开箱即射，不会枪弹错配。

### 1.0.4 修复

- **消耗品、近战、投掷物此前掉出来是空气。** 模组把它们写成了 `lrtactical:ai2`、`lrtactical:karambit` 这类物品名，但绿葡萄战术装备只注册了五个通用物品（`consumable`、`melee`、`throwable`、`flash_shield`、`detonator`），具体是哪个医疗包、哪把刀靠 NBT 区分。所以每一条消耗品/近战/投掷物掉落都是空的。现已改为「通用物品 + `ConsumableId` / `MeleeWeaponId` / `ThrowableId`」的正确写法。
- **AK-74M 三件套里的配件不存在。** `scope_okp7` 不是有效附件 ID（正确的是 `sight_okp7`），导致这组永远只掉枪和子弹、配件是空的。已修正。

### 1.0.4 新增

- **简体中文语言文件。** 中文客户端下方块名与创造模式物品栏显示为「幸运方块 TACZ」。
- **模组元数据补全。** `mods.toml` 新增 `issueTrackerURL`、128×128 的 `logoFile`、`displayTest="IGNORE_ALL_VERSION"`，与 TaCZ 版本不完全一致时不再显示红色警告。
- **掉落 ID 校验。** 新增 `validateLootIds` Gradle 任务，把代码里每一处掉落 ID 与自动生成的权威清单（`tools/reference-ids.json`）逐一比对，并挂进 `check`。以后枪、弹、附件、消耗品 ID 写错会**直接构建失败**，而不是静默掉出空气。

### 1.0.4 改进

- **地表神殿重做。** 生成的结构从单个悬空方块改为 5×3×5 的石砖平台：四角两根石砖柱、中央幸运方块、旁边一个地牢战利品箱。

### 沿用 1.0.3 的能力

70 把枪械 + 配套弹药、117 种单件附件、50 组「枪 + 弹药 + 附件」三件套、绿葡萄战术装备消耗品、11 把三角洲近战刀、地下矿脉生成（Y 60–120）、约 24 种箱子 10% 概率开出、9 个泥土合成配方。

### 前置依赖

- **必须**：TaCZ（`tacz`），版本 1.2.0
- **可选**：绿葡萄战术装备（`lrtactical`）
- **可选**：Delta WT（`delta_wt`）

### 更新方法

用本页的 `LuckyBlockTACZ-1.0.4.jar` 替换 `mods` 目录中的旧版本，重启游戏。

### 开发参考（Maven）

```gradle
repositories { maven { url "https://jitpack.io" } }
dependencies { implementation "com.github.SYSTEM-RAM-OP:LuckyBlockTACZ:1.0.4" }
```
