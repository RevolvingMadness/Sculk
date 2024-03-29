# Changelog

## 0.0.1

- Initial release
- The entire programming language

## 0.0.2

### Added

- Folder support for resources
    - `foo:bar` now supports `foo:foo/foobar`
- `as` keyword to import statements
- From statement
- Switch statement
- Switch expression
- Increment/decrement operators `x++` / `x--`
- Access to builtin classes
- Constructors for builtin classes
- Add ternary expressions
- Division by zero error
- `Entity.raycast(distance: Float, target: Block, check_fluids: Boolean): Boolean`
- `base64encode(input: String): String`
- `base64decode(input: String): String`
- `EntityTypes`
- `List.contains(object: Object): Boolean`

### Changed

- `Entity.extinguish(): Null` and `Entity.extinguishWithSound(): Null` have been merged into one
  function `Entity.extinguish(Boolean): Null`
- Fix circular imports
- Reverted custom load script
- Float extends Integer

## 0.0.3

### Added

- `abs(number: Integer | Float): Object`
- `String` is now iterable
- Data can now be written to an entity
- `String.startsWith(text: String): Boolean`
- `String.endsWith(text: String): Boolean`
- `break` can now be used in switch statement
- `String.split(splitter: String): List[String]`
- `Integer.parseInteger(integer: String): Integer`
- `Float.parseFloat(float: String): Float`
- `ceil(number: Integer | Float): Integer`
- `floor(number: Integer | Float): Integer`
- `BlockHitResult`
- `World.breakBlock(pos: BlockPos, drop_items: Boolean): Boolean`
- `World.getBlock(pos: BlockPos): Block`
- `List.append(object: Object): Null`

### Changed

- `events.onPlayerBreakBlock(function: Function[Boolean, PlayerEntity, Block]): Null` is
  now `events.onPlayerBreakBlock(function: Function[Boolean, PlayerEntity, BlockPos, Block]): Null`
- `World.setBlock` renamed to `World.placeBlock`
- `maxArguments` renamed to `maxArgumentCount`

### Fixed

- String concatenation
- Event registration not clearing on reload

### Improved

- Error messages

## 0.0.4

### Added

- `randomInteger(min: Integer, max: Integer) -> Integer`
- `randomFloat(min: Float, max: Float) -> Float`
- `String.length() -> Integer`
- `String.uppercase() -> String`
- `String.lowercase() -> String`
- `Number` type/class
- variables/fields have optional required types
- functions have required return types
- function arguments have required types
- methods have required return types
- method arguments have required types
- `ItemStack.item`

### Changed

- `Events` is now accessed from a static context
- `Entity.addCommandTag` -> `Entity.addTag`
- `Entity.getCommandTags` -> `Entity.getTags`
- `Entity.removeCommandTag` -> `Entity.removeTag`
- `Entity.dismountVehicle` -> `Entity.dismount`
- `Entity.removeAllPassengers` -> `Entity.removePassengers`
- `ServerPlayerEntity.changeGameMode` -> `ServerPlayerEntity.setGameMode`
- Integer now extends Float instead of Float extending Integer
- `Events.onPlayerAttackEntity` -> `Events.onAttackEntity`
- `Events.onPlayerBreakBlock` -> `Events.onBreakBlock`
- `Events.onPlayerCraftItem` -> `Events.onCraftItem`
- `Events.onPlayerDropItem` -> `Events.onDropItem`
- `Events.onPlayerJump` -> `Events.onJump`
- `Events.onPlayerPickupItem` -> `Events.onPickupItem`
- `Events.onPlayerRingBell` -> `Events.onRingBell`
- `Events.onPlayerSneak` -> `Events.whileSneaking`
- `Events.onPlayerUseItem` -> `Events.onRightClickItem`
- `Events.onPlayerSendChatMessage` -> `Events.onSendChatMessage`

### Fixed

- Dictionaries don't work in nbt elements
- Overriding `toString` does nothing
- A class with no constructor takes an infinite amount of arguments

### Improved

- Error messages

### Removed

- Resource syntax due to conflict with variable declaration parsing  
  New syntax: `namespace:path` -> `"namespace:path"`  
  Resources are just strings.

## 0.0.5

### Added

- `Item` extends `ItemStack`
- `PlayerEntity.getStackInMainHand`
- `PlayerEntity.getStackInOffHand`
- `Method` extends `Callable`
- `Function` extends `Callable`
- `Inventory` class
- `List` toString
- `ItemStack.init(item: Item, count: Integer) -> Null`
- `\uXXXX` to strings
- `String.fromUnicode(unicode: Integer) -> String`
- `GUI.onClose(player: PlayerEntity, gui: GUI) -> Null`
- `PlayerEntity.getEnderChestInventory() -> Inventory`
- `PlayerEntity.setEnderChestInventory(inventory: Inventory) -> Null`
- `Inventory` constructor
- `PlayerEntity.setInventory(inventory: Inventory) -> Null`
- `PlayerEntity.getInventory() -> Inventory`
- `NBTBoolean`
- `NBTCompound`
- `NBTElement`
- `NBTFloat`
- `NBTInteger`
- `NBTList`
- `NBTNull`
- `NBTString`
- Casting
- `start` script tag
- `nonnull` access modifier
- Custom item/block support
- `Items.register(item: Item) -> Item`
- `ItemSettings`
- `Blocks.register(block: Block) -> Block`
- `Blocks.registerWithItem(block: Block) -> Block`
- `BlockSettings`

# 0.0.6

## Added

- `SlabBlock`
- `WallBlock`
- `StairBlock`
- Custom keybinds
- `+=`
- `-=`
- `*=`
- `/=`
- `^=`
- `%=`
- If `->` not specified in function return types, will default to null.
- `SwordItem`
- `Entity.getPitch`
- `Entity.getYaw`
- Particle support

### NBT Serialization

- `BlockHitResult`
- `Block`
- `BlockPos`
- `BlockSettings`
