grammar testing;

program: statement*;

statement: (variable_assignment_statement SEMICOLON | import_statement SEMICOLON | if_statement SEMICOLON? | while_statement SEMICOLON? | for_statement SEMICOLON? | function_call_statement SEMICOLON | function_declaration_assignment SEMICOLON? | return_statement SEMICOLON | break_statement SEMICOLON | continue_statement SEMICOLON);

continue_statement: CONTINUE;

break_statement: BREAK;

return_statement: RETURN expression?;

function_declaration_assignment: FUNCTION IDENTIFIER LEFT_PARENTHESIS (IDENTIFIER (COMMA IDENTIFIER)*)? RIGHT_PARENTHESIS (RIGHT_ARROW IDENTIFIER)? body;

function_call_statement: IDENTIFIER LEFT_PARENTHESIS (expression (COMMA expression)*)? RIGHT_PARENTHESIS;

for_statement: FOR LEFT_PARENTHESIS variable_assignment_statement SEMICOLON expression SEMICOLON variable_assignment_statement SEMICOLON? RIGHT_PARENTHESIS body;

while_statement: WHILE LEFT_PARENTHESIS expression RIGHT_PARENTHESIS body;

if_statement: IF LEFT_PARENTHESIS expression RIGHT_PARENTHESIS body;

body: LEFT_BRACE statement* RIGHT_BRACE;

import_statement: IMPORT RESOURCE;

variable_assignment_statement: IDENTIFIER ((binary_operator? EQUALS expression) | (DOUBLE_PLUS | DOUBLE_HYPHEN));

expression: logical_expression;

logical_expression: addition_expression ((EQUAL_TO | NOT_EQUAL_TO | GREATER_THAN | GREATER_THAN_OR_EQUAL_TO | LESS_THAN | LESS_THAN_OR_EQUAL_TO) addition_expression)*;

addition_expression: multiplication_expression ((PLUS | HYPHEN) multiplication_expression)*;

multiplication_expression: unary_expression ((STAR | FSLASH | PERCENT) unary_expression)*;

unary_expression: (HYPHEN | EXCLAMATION_MARK)* exponentiation_expression;

exponentiation_expression: primary_expression (CARET primary_expression)*;

primary_expression: (INTEGER | FLOAT | (IDENTIFIER (LEFT_PARENTHESIS ( expression (COMMA expression)*)? RIGHT_PARENTHESIS)?) | LEFT_PARENTHESIS addition_expression RIGHT_PARENTHESIS | BOOLEAN | STRING | RESOURCE) | (LEFT_BRACKET (expression (COMMA expression)*)? RIGHT_BRACKET);

binary_operator: PLUS | HYPHEN | STAR | FSLASH | PERCENT | CARET;

FLOAT: (INTEGER PERIOD INTEGER?) | (INTEGER? PERIOD INTEGER);
RESOURCE: IDENTIFIER ':' IDENTIFIER;
STRING: '"' .*? '"';
IMPORT: 'import';
BOOLEAN: 'true' | 'false';
PLUS: '+';
DOUBLE_PLUS: '++';
HYPHEN: '-';
DOUBLE_HYPHEN: '--';
STAR: '*';
FSLASH: '/';
PERCENT: '%';
CARET: '^';
EXCLAMATION_MARK: '!';
LEFT_PARENTHESIS: '(';
RIGHT_PARENTHESIS: ')';
EQUAL_TO: '==';
NOT_EQUAL_TO: '!=';
GREATER_THAN: '>';
GREATER_THAN_OR_EQUAL_TO: '>=';
LESS_THAN: '<';
LESS_THAN_OR_EQUAL_TO: '<=';
FUNCTION: 'function';
FOR: 'for';
WHILE: 'while';
IF: 'if';
LEFT_BRACE: '{';
RIGHT_BRACE: '}';
EQUALS: '=';
SEMICOLON: ';';
RIGHT_ARROW: '->';
RETURN: 'return';
BREAK: 'break';
CONTINUE: 'continue';
COMMA: ',';
PERIOD: '.';
LEFT_BRACKET: '[';
RIGHT_BRACKET: ']';

IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;
INTEGER: [0-9]+;

WHITESPACE: [ \r\n\t] -> skip;