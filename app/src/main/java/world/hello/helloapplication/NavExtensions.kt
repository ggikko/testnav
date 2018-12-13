package world.hello.helloapplication

import android.annotation.SuppressLint
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.ref.WeakReference

fun setUpWithNav(
    bottomNavigationView: BottomNavigationView,
    navController: NavController
) {

    bottomNavigationView.setOnNavigationItemSelectedListener(
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                return NavigationUI.onNavDestinationSelected(item, navController)
            }
        })
    val weakReference = WeakReference<BottomNavigationView>(bottomNavigationView)
    navController.addOnNavigatedListener(object : NavController.OnNavigatedListener {
        override fun onNavigated(
            @NonNull controller: NavController,
            @NonNull destination: NavDestination
        ) {
            val view = weakReference.get()
            if (view == null) {
                controller.removeOnNavigatedListener(this)
                return
            }
            val menu = view.menu
            var h = 0
            val size = menu.size()
            while (h < size) {
                val item = menu.getItem(h)
                if (matchDestination(destination, item.itemId)) {
                    item.isChecked = true
                }
                h++
            }
        }
    })
}

internal fun matchDestination(
    @NonNull destination: NavDestination, @IdRes destId: Int
): Boolean {
    var currentDestination: NavDestination? = destination
    while (currentDestination!!.id != destId && currentDestination.parent != null) {
        currentDestination = currentDestination.parent
    }
    return currentDestination.id == destId
}

@SuppressLint("RestrictedApi")
fun disableShiftMode(view: BottomNavigationView) {
    val menuView = view.getChildAt(0) as BottomNavigationMenuView
    for (i in 0 until menuView.childCount) {
        val item = menuView.getChildAt(i) as BottomNavigationItemView
        item.setShifting(false)
        item.setChecked(item.itemData.isChecked)
    }
}
