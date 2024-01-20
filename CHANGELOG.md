# Changelog

## 0.0.1

- Initial release
- The entire programming language

## 0.0.2

### Added

- Added folder support for resources
    - `foo:bar` now supports `foo:foo/foobar`
- Add `as` keyword to import statements
- Add from statement
- Add switch statement
- Add switch expression
- Add increment/decrement operators `x++` / `x--`

### Changed

- `Entity.extinguish(): Null` and `Entity.extinguishWithSound(): Null` have been merged into one
  function `Entity.extinguish(Boolean): Null`
- Fix circular imports
- Reverted custom load script
