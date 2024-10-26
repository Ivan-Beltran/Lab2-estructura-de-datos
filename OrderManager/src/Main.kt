import java.util.*

fun mostrarOpciones() { //creando funcion para mostrar el menu de opciones
    println("Bienvenido a la Pupusería \"El Comalito\"")
    println("Seleccione una opción:")
    println("1. Registrar una nueva orden")
    println("2. Ver órdenes pendientes")
    println("3. Despachar orden")
    println("4. Ver órdenes despachadas")
    println("5. Salir")
    println("ingrese una opcion :")
}

fun main() {
    val menuPupusas = listOf("revueltas", "frijol con queso", "queso")
    val ordenesPendientes: Queue<Orden> = LinkedList()
    val ordenesDespachadas: Stack<Orden> = Stack()
    val pupuseria = Pupuseria(menuPupusas, ordenesPendientes, ordenesDespachadas) // clase con los metodos de una pupuseria

    while (true) {
        mostrarOpciones()
        var opcionSeleccionada = readln()
        println()
        while (!ValidarNumero(opcionSeleccionada)){
            println("Ingrese un numero valido: ")
            opcionSeleccionada= readln()
        }

        when (opcionSeleccionada.toInt()) {
            1 -> pupuseria.agregarOrden()
            2 -> pupuseria.verOrdenesPendientes()
            3 -> pupuseria.despacharOrden()
            4 -> pupuseria.verOrdenesDespachadas()
            5 -> {
                println("Saliendo del programa.")
                break
            }
            else -> println("Opción inválida, por favor intente de nuevo. \n")
        }
    }
}