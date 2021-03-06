package com.riluq.footballmatchschedule


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.riluq.footballmatchschedule.adapter.FavoriteAdapter
import com.riluq.footballmatchschedule.database.Favorite
import com.riluq.footballmatchschedule.utils.database
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout


/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteMatchFragment : Fragment(), AnkoComponent<Context> {

    private lateinit var listEvent: RecyclerView

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapter: FavoriteAdapter

    private var favorites: MutableList<Favorite> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoriteAdapter(requireActivity(), favorites){
            startActivity<MatchDetailActivity>(
                    "idEvent" to "${it.eventId}",
                    "teamIdHome" to "${it.teamHomeId}",
                    "teamIdAway" to "${it.teamAwayId}")
        }
        listEvent.adapter = adapter

        showFavorite()
        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    private fun showFavorite() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            padding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                id = R.id.srl_prev_match
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)


                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listEvent = recyclerView {
                        id = R.id.rv_prev_match
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)

                    }

                }
            }

        }
    }


}
