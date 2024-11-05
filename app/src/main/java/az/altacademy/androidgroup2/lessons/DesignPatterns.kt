package az.altacademy.androidgroup2.lessons

import az.altacademy.androidgroup2.utils.apiCall


interface Notification {
    fun showNotification(message: String)
}

@Deprecated("reason")
class SuccessNotification() : Notification {

    override fun showNotification(message: String) {
        println("SUCCESS: $message")
    }
}

class SuccessOperation : Notification {
    override fun showNotification(message: String) {
        println("SUCCESS: $message")
    }

}

class WarningNotification() : Notification {

    override fun showNotification(message: String) {
        println("WARNING: $message")
    }
}

enum class NotificationType {
    SUCCESS, WARNING
}

object NotificationFactory {
    fun createNotification(notificationType: NotificationType): Notification {
        return when (notificationType) {
            NotificationType.SUCCESS -> SuccessOperation()
            NotificationType.WARNING -> WarningNotification()
        }
    }
}


class FirebaseClient private constructor(databaseName: String, collectionName: String? = null) {

    class Builder(private val databaseName: String) {
        private var collectionName: String? = null

        fun setCollectionName(collectionName: String) = apply {
            this.collectionName = collectionName
        }

        fun build(): FirebaseClient {
            return FirebaseClient(databaseName = databaseName, collectionName = collectionName)
        }
    }

}


data class UserModel(
    val name: String,
    val surname: String,
    val age: Int,
    val email: String,
    val address: String
)

data class UserUIModel(
    var fullName: String,
    var age: Int,
    var email: String
)


class UserMapper(){
    fun toUIUser(user: UserModel): UserUIModel{
        return UserUIModel(
            fullName = "${user.name} ${user.surname}",
            age = user.age,
            email = user.email
        )
    }
}



interface MenuComponent{
    fun display(intend: String = "")
}

class MenuItem(private val name: String): MenuComponent{
    override fun display(intend: String) {
        println("$intend -$name")
    }
}

class MenuGroup(private val name: String): MenuComponent{

    private val components = mutableListOf<MenuComponent>()

    fun addSubMenu(subMenu: MenuComponent){
        components.add(subMenu)
    }

    override fun display(intend: String) {
        println("$intend + $name")
        components.forEach{ item->
            item.display("$intend  ")
        }
    }

}


fun main() {

//    val menuItem1 = MenuItem("Item 1")
//    val menuItem2 = MenuItem("Item 2")
//    val menuItem3 = MenuItem("Sub Item 2")
//
//    val menuSubItem1 = MenuItem("Sub Item 1")
//    val menuSubItem2 = MenuItem("Sub Item 2")
//    val menuGroup = MenuGroup("Group1")
//    menuGroup.addSubMenu(menuSubItem1)
//    menuGroup.addSubMenu(menuSubItem2)
//
//    val mainMenu = MenuGroup("")
//    mainMenu.addSubMenu(menuItem1)
//    mainMenu.addSubMenu(menuItem2)
//    mainMenu.addSubMenu(menuGroup)
//    mainMenu.addSubMenu(menuItem3)
//
//    mainMenu.display()


//    var apiList = createList()
//    val mapper= UserMapper()
//    val newList = apiList.map { user ->
//        mapper.toUIUser(user)
//    }
//
//    println(apiList)
//    println(newList)

//    val firebaseClient = FirebaseClient.Builder("text")
//        .setCollectionName("student")
//        .build()
//
//    val successNotification = SuccessOperation()
//    successNotification.showNotification("test")
//
//    val warningNotification = WarningNotification()
//    warningNotification.showNotification("test")
//    // success
//    NotificationFactory.createNotification(NotificationType.SUCCESS).showNotification("test")


    val cardList = createCardList()
    val activeCards = cardList.filter { it.cardStatus == CardStatus.ACTIVE }
    val expiredCards = cardList.filter { it.cardStatus == CardStatus.EXPIRED }
    val blockedCards = cardList.filter { it.cardStatus == CardStatus.BLOCKED || it.cardStatus == CardStatus.PHYSICAL_BLOCKED }

    println(activeCards)
    println(expiredCards)
    println(blockedCards)

}

enum class CardStatus{
    ACTIVE, EXPIRED, BLOCKED, PHYSICAL_BLOCKED
}


private fun createCardList() = listOf(
    CardModel(
        "Albali",
        34.5,
        CardStatus.ACTIVE
    ),
    CardModel(
        "Premium",
        134.5,
        CardStatus.ACTIVE
    ),
    CardModel(
        "Standart",
        304.0,
        CardStatus.BLOCKED
    ),
    CardModel(
        "Platinum",
        0.0,
        CardStatus.EXPIRED
    ),
    CardModel(
        "Infinity",
        3445.0,
        CardStatus.BLOCKED
    ),
)


data class CardModel(
    var name: String,
    var balance: Double,
    var cardStatus: CardStatus
)




fun createList() = listOf(
    UserModel(
        name = "Arif",
        surname = "Memmedov",
        age = 22,
        email = "arif@gmail.com",
        address = "Yasamal"
    ),
    UserModel(
        name = "Fuad",
        surname = "Haciyev",
        age = 31,
        email = "fuad@gmail.com",
        address = "Nizami"
    ),
    UserModel(
        name = "Samir",
        surname = "Nebiyev",
        age = 21,
        email = "salmir@gmail.com",
        address = "Nerimanov"
    ),
)



