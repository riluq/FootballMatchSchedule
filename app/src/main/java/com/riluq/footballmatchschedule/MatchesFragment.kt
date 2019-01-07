//package com.riluq.footballmatchschedule
//
//
//import android.os.Bundle
//import android.support.design.widget.Snackbar
//import android.support.design.widget.TabLayout
//import android.support.v4.app.Fragment
//import android.support.v4.app.FragmentManager
//import android.support.v4.app.FragmentPagerAdapter
//import android.view.*
//import kotlinx.android.synthetic.main.activity_coba.*
//import kotlinx.android.synthetic.main.fragment_coba.view.*
//
//
///**
// * A simple [Fragment] subclass.
// *
// */
//class MatchesFragment : Fragment() {
//
//    private var mSectionsPagerAdapter: CobaActivity.SectionsPagerAdapter? = null
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        setSupportActionBar(toolbar)
//        setHasOptionsMenu(true)
//        // Create the adapter that will return a fragment for each of the three
//        // primary sections of the activity.
//        mSectionsPagerAdapter = MatchesFragment.SectionsPagerAdapter(supportFragmentManager)
//
//        // Set up the ViewPager with the sections adapter.
//        container.adapter = mSectionsPagerAdapter
//
//        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
//        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_matches, container, false)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        inflater?.inflate(R.menu.menu_coba, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        val id = item.itemId
//
//        if (id == R.id.action_settings) {
//            return true
//        }
//
//        return super.onOptionsItemSelected(item)
//    }
//
//
//    /**
//     * A [FragmentPagerAdapter] that returns a fragment corresponding to
//     * one of the sections/tabs/pages.
//     */
//    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
//
//        override fun getItem(position: Int): Fragment {
//            // getItem is called to instantiate the fragment for the given page.
//            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1)
//        }
//
//        override fun getCount(): Int {
//            // Show 3 total pages.
//            return 3
//        }
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    class PlaceholderFragment : Fragment() {
//
//        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                                  savedInstanceState: Bundle?): View? {
//            val rootView = inflater.inflate(R.layout.fragment_coba, container, false)
//            rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
//            return rootView
//        }
//
//        companion object {
//            /**
//             * The fragment argument representing the section number for this
//             * fragment.
//             */
//            private val ARG_SECTION_NUMBER = "section_number"
//
//            /**
//             * Returns a new instance of this fragment for the given section
//             * number.
//             */
//            fun newInstance(sectionNumber: Int): PlaceholderFragment {
//                val fragment = PlaceholderFragment()
//                val args = Bundle()
//                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
//                fragment.arguments = args
//                return fragment
//            }
//        }
//    }
//
//}
