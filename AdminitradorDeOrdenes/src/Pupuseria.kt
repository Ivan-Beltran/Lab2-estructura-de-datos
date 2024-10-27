package PupuseriaClass
import java.util.*
import validations.ValidarString
import validations.ValidarNumero

class Pupusas(val tipo: String, val cantidad: Int)

class Orden(val cliente: String, val pupusasPedidas: List<Pupusas>)

fun MostrarMenu(menuPupusas: List<String>) {
    println("Menú de pupusas:")
    menuPupusas.forEach { especialidad ->
        println("- $especialidad")
    }
}

class Pupuseria(
    val menuPupusas: List<String>,
    val ordenPendiente: Queue<Orden>,
    val ordenDespachada: Stack<Orden>
) {
    fun agregarOrden() {
        println("Ingrese el nombre del cliente: ")
        var cliente = readln()
        while (!ValidarString(cliente)){
            println("el nombre no puede estar vacio: ")
            cliente = readln()
        }

        val pupusasPedidas = mutableListOf<Pupusas>()
        MostrarMenu(menuPupusas)

        println("Ingrese la cantidad de tipos de pupusas diferentes desea: ")
        var cantidadTipos = readln()
        while (!ValidarNumero(cantidadTipos)){
            println("Ingrese un numero valido: ")
            cantidadTipos= readln()
        }
        while(cantidadTipos.toInt()>menuPupusas.size || cantidadTipos.toInt()<1){
            println("Ingrese un numero valido: ")
            cantidadTipos= readln()
        }
        val especialidadesIngresadas = mutableSetOf<String>()

        for (i in 1..cantidadTipos.toInt()) {
            println("Ingrese la especialidad número $i de pupusas:")
            var especialidad = readln()

            while(!menuPupusas.contains(especialidad) || especialidadesIngresadas.contains(especialidad)) {
                if (!menuPupusas.contains(especialidad)) {
                    println("Esa opción no está disponible en el menú.")
                } else {
                    println("Esa especialidad ya ha sido ingresada. Ingrese una especialidad diferente:")
                }
                especialidad = readln()
            }

            especialidadesIngresadas.add(especialidad)

            println("Ingrese la cantidad de pupusas de $especialidad que desea:")
            var cantidadPupusas = readln()
            while (!ValidarNumero(cantidadPupusas)) {
                println("Ingrese un número válido: ")
                cantidadPupusas = readln()
            }

            val pupusasSeleccionadas = Pupusas(especialidad, cantidadPupusas.toInt())
            pupusasPedidas.add(pupusasSeleccionadas)
        }

        val nuevaOrden = Orden(cliente, pupusasPedidas)
        ordenPendiente.add(nuevaOrden)
        println("Orden agregada para $cliente. \n\n")
    }

    fun despacharOrden() {
        if (ordenPendiente.isNotEmpty()) {
            val ordenADespachar = ordenPendiente.poll()
            ordenDespachada.push(ordenADespachar) 
            println("Orden de ${ordenADespachar.cliente} ha sido despachada.\n\n")
        } else {
            println("No hay órdenes pendientes para despachar.\n")
        }
    }

    fun verOrdenesPendientes() {
        if (ordenPendiente.isNotEmpty()) {
            println("Órdenes pendientes: ${ordenPendiente.size}")
            ordenPendiente.forEach { orden ->
                val detalles = orden.pupusasPedidas.joinToString { "${it.cantidad} de ${it.tipo}" }
                println("${orden.cliente}: $detalles \n")
            }
        } else {
            println("No hay órdenes pendientes.\n")
        }
    }

    fun verOrdenesDespachadas() {
        if (ordenDespachada.isNotEmpty()) {
            println("Órdenes despachadas: ")
            ordenDespachada.forEach { orden ->
                val detalles = orden.pupusasPedidas.joinToString { "${it.cantidad} de ${it.tipo}" }
                println("${orden.cliente}: $detalles \n")
            }
        } else {
            println("No hay órdenes despachadas.\n")
        }
    }
}
