# Notas - crud básico em kotlin

Um aprendizado extra foi o [**copy**](https://kotlinlang.org/docs/data-classes.html#copying) do kotlin, permite fazer uma cópia do objeto apenas encadeando a chamada de 'copy'.

- Exemplo:

```
val jack = User(name = "Jack", age = 1)
val olderJack = jack.copy(age = 2)
```

- Authors

[**Diorgenes Morais**](https://github.com/diorgenesmorais)
