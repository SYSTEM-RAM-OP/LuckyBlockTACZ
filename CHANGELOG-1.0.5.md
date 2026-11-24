## LuckyBlock TACZ 1.0.5

**Minecraft 1.20.1 · Forge**

### Overview

LuckyBlock TACZ is a lightweight lucky-block add-on for TaCZ. It does **not** require the original Lucky Block mod. Break the block to roll a random TaCZ firearm together with perfectly matched ammo, ready to fire, no mismatches.

### Added

- **Config file with hot reload.** A new `config/luckyblocktacz-common.toml` exposes every tuning knob: per-tier drop chances, per-rarity weights, how many drop sets per break, scatter radius, jackpot trigger and reward, advancement toggles, and render options. Edit the file and it applies automatically, or run `/lbtacz reload` (op level 2) to refresh the cached loot table without restarting.
- **Five rarity tiers with two-layer weighting.** Drops are now rolled in two passes — first a tier (1–4), then a rarity inside that tier. Default distribution: Common 44.0%, Uncommon 32.1%, Rare 17.0%, Epic 5.9%, Legendary 1.0%. All tunable in the config. Gun + ammo + attachment bundles still drop together in a single tick, never mismatched.
- **Multi-block jackpot.** Breaking several lucky blocks within a short window inside a small radius triggers a jackpot that drops 6 top-tier sets. Configurable threshold, radius, window and reward. A server-wide announcement and a level-up sound play when it fires.
- **Advancements.** Five new achievements — 幸运方块 TACZ (root), First Gun, First Epic, Jackpot and Collector (break 100 blocks) — fully localized in both English and Chinese.
- **Block-entity renderer.** The block now hosts a block entity that renders a slowly spinning, gently bobbing lucky-block item floating in the middle of the block, purely client-side.

### Improved

- Empty-drop protection: every weighted pool falls back gracefully (re-normalize, then skip to the next non-empty rarity bucket) instead of dropping nothing or throwing.

### Carried over from 1.0.4

70 guns with matched ammo, 117 individual attachments, 50 gun + ammo + attachment bundles, consumables with Green Grape Tactical Gear, the 11 Delta WT knives, underground ore generation (Y 60–120), ~24 chest loot tables at 10% chance, a 9-dirt crafting recipe, Simplified Chinese localization, and the reworked stone-brick shrine structure.

### Requirements

- **Required**: TaCZ (`tacz`) 1.2.0
- **Optional**: Green Grape Tactical Gear (`lrtactical`)
- **Optional**: Delta WT (`delta_wt`)

### Update

Replace the old jar in your `mods` folder with `LuckyBlockTACZ-1.0.5.jar` and restart the game. On your first launch a default `config/luckyblocktacz-common.toml` is generated.

### Maven (for developers)

```gradle
repositories { maven { url "https://jitpack.io" } }
dependencies { implementation "com.github.SYSTEM-RAM-OP:LuckyBlockTACZ:1.0.5" }
```

---

## LuckyBlock TACZ 1.0.5（中文）

**Minecraft 1.20.1 · Forge**

### 模组简介

LuckyBlock TACZ 是一个轻量级的 TaCZ 幸运方块模组，**不依赖原版 Lucky Block 模组**。挖开幸运方块，随机获得一把 TaCZ 枪械，并同时掉落与之口径完全匹配的弹药，开箱即射，不会枪弹错配。

### 1.0.5 新增

- **配置文件 + 热重载。** 新增 `config/luckyblocktacz-common.toml`，把所有可调参数都暴露出来：各 tier 掉落概率、各稀有度权重、每次破坏掉几组、散落半径、大奖触发条件与奖励、进度开关、渲染开关。改完文件自动生效；也可以执行 `/lbtacz reload`（OP 等级 2）立刻刷新缓存战利品表，无需重启游戏。
- **五档稀有度 + 两层加权。** 掉落改为两层抽取——先抽 tier（1–4），再在该 tier 内抽稀有度。默认分布：Common 44.0%、Uncommon 32.1%、Rare 17.0%、Epic 5.9%、Legendary 1.0%，全部可在配置里调整。「枪 + 弹药 + 配件」仍在一个 tick 内一起掉落，绝不枪弹错配。
- **多方块大奖。** 在很短的时间窗口内、很小的范围内连续挖开多个幸运方块，即可触发大奖，一次掉落 6 组最高 tier 奖励。阈值、范围、时间窗、奖励数量都可在配置里调整。触发时全服公告并播放升级音效。
- **进度（成就）。** 新增五个进度——幸运方块 TACZ（根）、第一把枪、第一件史诗、大奖、收藏家（累计挖 100 块）——中英双语完整本地化。
- **方块实体渲染。** 方块现在带一个方块实体，在方块正中央渲染一个缓缓自转、微微上下浮动的幸运方块物品，纯客户端表现，不影响服务器。

### 1.0.5 改进

- 空掉落防护：每个加权池都做了优雅回退（归一化后跳到下一个非空稀有度桶），宁可换奖品也绝不掉出空气或抛异常。

### 沿用 1.0.4 的能力

70 把枪械 + 配套弹药、117 种单件附件、50 组「枪 + 弹药 + 附件」三件套、绿葡萄战术装备消耗品、11 把三角洲近战刀、地下矿脉生成（Y 60–120）、约 24 种箱子 10% 概率开出、9 个泥土合成配方、简体中文语言文件、重做后的石砖神殿结构。

### 前置依赖

- **必须**：TaCZ（`tacz`），版本 1.2.0
- **可选**：绿葡萄战术装备（`lrtactical`）
- **可选**：Delta WT（`delta_wt`）

### 更新方法

用本页的 `LuckyBlockTACZ-1.0.5.jar` 替换 `mods` 目录中的旧版本，重启游戏。首次启动会自动生成默认的 `config/luckyblocktacz-common.toml`。

### 开发参考（Maven）

```gradle
repositories { maven { url "https://jitpack.io" } }
dependencies { implementation "com.github.SYSTEM-RAM-OP:LuckyBlockTACZ:1.0.5" }
```