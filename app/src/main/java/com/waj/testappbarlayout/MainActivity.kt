package com.waj.testappbarlayout

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

/**
 * ActionBar已经绑定到Activity中，独立于页面布局，与Activity紧密地耦合在一起，难于定制，也无法与其它控件联动，这也是大多数公司用自定义视图代替
 * ActionBar，模拟导航栏的功能，禁用系统自带的ActionBar的原因。
 * 为了将导航栏与activity完全解耦，使其重归页面布局的怀抱，MD创建了Toobar，完全代替ActionBar的功能。
 *
 * */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**导航栏*/
        setSupportActionBar(toolbar)

        /**抽屉布局*/
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        /**抽屉布局中的导航视图*/
        nav_view.setNavigationItemSelectedListener(this)

        val adapter = SimpleAdapter(supportFragmentManager)
        //用于管理切换的页面，根据不同的滑动位置显示对应的页面
        mVpContainer.adapter = adapter
        //viewpager滑动时，监听器中的相应方法会被调用
        mVpContainer.addOnPageChangeListener(MyPageChangeListener.newInstance(adapter,appbar_iv_outgoing,appbar_iv_target))

        title = "changed what you want"

        //联动效果
        tabLayout.setupWithViewPager(mVpContainer)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
