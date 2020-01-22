package com.xfinity.wireviewer

import android.app.SearchManager.QUERY
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import com.xfinity.R
import com.xfinity.features.masterdetail.CharacterViewModel
import com.xfinity.features.masterdetail.fragment.ItemDetailFragment
import com.xfinity.features.masterdetail.fragment.ItemListFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    private var itemListFragment: ItemListFragment? = null
    private var tabletSize: Boolean = false
    private var showIcon: Boolean = false
    private var toolbar: Toolbar? = null
    private lateinit var myMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onResume() {
        super.onResume()

        val bundle = Bundle()
        bundle.putString(QUERY, getString(R.string.search_query))

        itemListFragment = ItemListFragment()
        itemListFragment!!.retainInstance = true
        itemListFragment!!.arguments = bundle

        itemListFragment!!.showIcon = showIcon

        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down,
                R.anim.slide_up, R.anim.slide_down)

        fragmentTransaction.replace(R.id.framelayout_left, itemListFragment)

        if (findViewById<View>(R.id.framelayout_right) != null) {
            val itemDetailFragment = ItemDetailFragment()
            itemDetailFragment.retainInstance = true
            fragmentTransaction.replace(R.id.framelayout_right, itemDetailFragment)
        }

        fragmentTransaction.commit()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(url: String) {
        println("_xyz $url")
        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        if (!tabletSize) {
            fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit,
                    R.anim.pop_enter, R.anim.pop_exit)
        }

        var containerViewId = R.id.framelayout_left

        if (findViewById<View>(R.id.framelayout_right) != null)
            containerViewId = R.id.framelayout_right

        val bundle = Bundle()
        bundle.putString("URL", url)

        val itemDetailFragment = ItemDetailFragment()
        itemDetailFragment.retainInstance = true
        itemDetailFragment.arguments = bundle
        fragmentTransaction.replace(containerViewId, itemDetailFragment)

        if (findViewById<View>(R.id.framelayout_right) == null)
            fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        state.putBoolean(SHOWICON, showIcon)
    }

    companion object {
        private val MENU_ITEM_ITEM1 = 1
        private val SHOWICON = "showIcon"
    }

}