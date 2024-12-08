package org.example

fun main() {
    var input = ""
    val array = intArrayOf(1, 2, 3)
    val result = mutableListOf<List<Int>>()
    generatePermutations(array.toList(), mutableListOf(), result)

    // Вывод результата
    result.forEach { println(it) }
    while (true) {
        println("Введите строку")

        input = readln()
        if (checkingSymmetryBrackets(input)) {
            println("Строка симметрична")
        } else {
            println("Строка не симметрична")
        }
    }

}

fun checkingSymmetryBrackets(string: String): Boolean {
    val brackets = mapOf('(' to ')', '[' to ']', '{' to '}')
    val stack = mutableListOf<Char>()

    for (char in string) {
        if (char in brackets.keys) {
            stack.add(char) // Открывающая скобка добавляется в стек
        } else if (char in brackets.values) {
            // Если стек пуст или последняя открывающая скобка не соответствует текущей закрывающей
            if (stack.isEmpty() || brackets[stack.removeAt(stack.lastIndex)] != char) {
                return false
            }
        }
    }
    return stack.isEmpty() // Все скобки должны быть сбалансированы
}

fun generatePermutations(
    input: List<Int>,
    current: MutableList<Int>,
    result: MutableList<List<Int>>
) {
    if (input.isEmpty()) {
        // Если нет больше элементов для перестановки, добавляем текущую комбинацию в результат
        result.add(current.toList())
        return
    }

    for (i in input.indices) {
        val remaining = input.toMutableList()
        val next = remaining.removeAt(i) // Убираем текущий элемент из оставшихся
        current.add(next) // Добавляем его к текущей перестановке
        generatePermutations(remaining, current, result)
        current.removeAt(current.lastIndex) // Убираем последний элемент для следующей итерации
    }
}
