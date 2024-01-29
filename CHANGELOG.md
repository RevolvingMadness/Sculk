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