fun optionMenu(): String {
    println("Bienvenido a pupusería El Marlony")
    println("Seleccione una opción:")
    println("1. Registrar una nueva orden")
    println("2. Ver órdenes pendientes")
    println("3. Despachar orden")
    println("4. Ver órdenes despachadas")
    println("5. Salir")
    print("Opción: ")
    return readlnOrNull() ?: ""
}

class Pupusas(val type:String,val quantity:Int){
    override fun toString(): String {
        return "${quantity} pupusas de ${type}"
    }
}

class Order(val client:String,val pupusasOrder:List<Pupusas>){
    fun ShowOrder(){
        println("la orden del cliente ${client} es :")
        for(pupusas in pupusasOrder){
            println(pupusas)
        }
    }
}

fun clearConsole() {
    System.out.flush()
}

