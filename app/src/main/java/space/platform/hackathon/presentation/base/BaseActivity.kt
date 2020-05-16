package space.platform.hackathon.presentation.base

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import space.platform.hackathon.util.state.State
import space.platform.hackathon.util.state.StateListener
import space.platform.hackathon.util.ui.UIUtil

abstract class BaseActivity : AppCompatActivity(), StateListener {

    abstract fun layoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
    }

    override fun onStateChange(dataState: State<*>) {
        handleStateChange(dataState)
    }

    private fun handleStateChange(dataState: State<*>) {
        dataState.message?.let { message ->
            UIUtil.showErrorMessage(this, dataState.message)
        }
    }

    fun initToolbar(toolbar: Toolbar, title: String, isHomeScreen: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        if (!isHomeScreen) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    fun initToolbar(toolbar: Toolbar, navigationIcon: Drawable, title: String, isHomeScreen: Boolean) {
        toolbar.navigationIcon = navigationIcon
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        if (!isHomeScreen) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}