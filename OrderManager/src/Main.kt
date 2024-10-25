import java.util.LinkedList
import java.util.Queue
import java.util.Stack

class Pupusas(val tipo: String, val cantidad: Int)

class Orden(val cliente: String, val pupusasPedidas: List<Pupusas>)

fun MostrarMenu(menuPupusas: List<String>) {
    println("Menú de pupusas:")
    menuPupusas.forEach { especialidad ->
        println("- $especialidad")
    }
}
fun mostrarOpciones() {
    println("Bienvenido a la Pupusería \"El Comalito\"")
    println("Seleccione una opción:")
    println("1. Registrar una nueva orden")
    println("2. Ver órdenes pendientes")
    println("3. Despachar orden")
    println("4. Ver órdenes despachadas")
    println("5. Salir")
}

class Pupuseria(
    val menuPupusas: List<String>,
    val ordenPendiente: Queue<Orden>,
    val ordenDespachada: Stack<Orden>
) {
    fun agregarOrden() {
        println("Ingrese el nombre del cliente: ")
        val cliente = readln()

        val pupusasPedidas = mutableListOf<Pupusas>()
        MostrarMenu(menuPupusas)

        println("Ingrese cuántos tipos de pupusas diferentes desea: ")
        val cantidadTipos = readln().toInt()

        for (i in 1..cantidadTipos) {
            println("Ingrese la especialidad número $i de pupusas:")
            val especialidad = readln()

            if (!menuPupusas.contains(especialidad)) {
                println("Esa opción no está disponible en el menú.")
                continue
            }

            println("Ingrese la cantidad de pupusas de $especialidad que desea:")
            val cantidadPupusas = readln().toInt()

            val pupusasSeleccionadas = Pupusas(especialidad, cantidadPupusas)
            pupusasPedidas.add(pupusasSeleccionadas)
        }

        val nuevaOrden = Orden(cliente, pupusasPedidas)
        ordenPendiente.add(nuevaOrden)
        println("Orden agregada para $cliente.")
    }

    fun despacharOrden() {
        if (ordenPendiente.isNotEmpty()) {
            val ordenADespachar = ordenPendiente.poll() // Retira y obtiene la primera orden de la cola
            ordenDespachada.push(ordenADespachar) // Agrega la orden a la pila de órdenes despachadas
            println("Orden de ${ordenADespachar.cliente} ha sido despachada.")
        } else {
            println("No hay órdenes pendientes para despachar.")
        }
    }

    fun verOrdenesPendientes() {
        if (ordenPendiente.isNotEmpty()) {
            println("Órdenes pendientes:")
            ordenPendiente.forEach { orden ->
                val detalles = orden.pupusasPedidas.joinToString { "${it.cantidad} de ${it.tipo}" }
                println("${orden.cliente}: $detalles")
            }
        } else {
            println("No hay órdenes pendientes.")
        }
    }

    fun verOrdenesDespachadas() {
        if (ordenDespachada.isNotEmpty()) {
            println("Órdenes despachadas:")
            ordenDespachada.forEach { orden ->
                val detalles = orden.pupusasPedidas.joinToString { "${it.cantidad} de ${it.tipo}" }
                println("${orden.cliente}: $detalles")
            }
        } else {
            println("No hay órdenes despachadas.")
        }
    }
}

fun main() {
    val menuPupusas = listOf("revueltas", "frijol con queso", "queso")
    val ordenesPendientes: Queue<Orden> = LinkedList()
    val ordenesDespachadas: Stack<Orden> = Stack()
    val pupuseria = Pupuseria(menuPupusas, ordenesPendientes, ordenesDespachadas)

    var continuar = true
    while (continuar) {
        mostrarOpciones() // Mostrar el menú de opciones
        val opcionSeleccionada = readln().toInt() // Leer la opción elegida por el usuario

        when (opcionSeleccionada) {
            1 -> pupuseria.agregarOrden() // Agregar una nueva orden
            2 -> pupuseria.verOrdenesPendientes() // Ver órdenes pendientes
            3 -> pupuseria.despacharOrden() // Despachar la primera orden
            4 -> pupuseria.verOrdenesDespachadas() // Ver órdenes despachadas
            5 -> {
                println("Saliendo del programa.")
                continuar = false // Salir del bucle
            }
            else -> println("Opción inválida, por favor intente de nuevo.") // Opción no válida
        }
    }
}