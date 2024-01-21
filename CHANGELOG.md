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

### Changed

- `Entity.extinguish(): Null` and `Entity.extinguishWithSound(): Null` have been merged into one
  function `Entity.extinguish(Boolean): Null`
- Fix circular imports
- Reverted custom load script
