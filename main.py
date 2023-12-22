class Foo(int):
    def __init__(self):
        print(type(super()))

    def thing(self):
        ...


print(Foo().thing())
