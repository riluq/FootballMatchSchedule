package com.riluq.footballmatchschedule

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.riluq.footballmatchschedule.api.ApiRepository
import com.riluq.footballmatchschedule.model.PlayerDetails
import com.riluq.footballmatchschedule.presenter.PlayerDetailsPresenter
import com.riluq.footballmatchschedule.utils.invisible
import com.riluq.footballmatchschedule.utils.visible
import com.riluq.footballmatchschedule.view.PlayerDetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_details.*



class PlayerDetailsActivity : AppCompatActivity(), PlayerDetailView {
    private lateinit var idPlayer: String
    private lateinit var playerName: String


    private lateinit var presenter: PlayerDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        idPlayer = intent.getStringExtra("id")
        playerName = intent.getStringExtra("playerName")

        supportActionBar?.title = playerName

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerDetailsPresenter(this, request, gson)
        presenter.getTeamDetail(idPlayer)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        pb_player_detail.visible()
    }

    override fun hideLoading() {
        pb_player_detail.invisible()
    }

    override fun showPlayerDetailList(player: List<PlayerDetails>) {
//        players = PlayerDetails(player[0].playerPhoto,
//                player[0].playerHeight,
//                player[0].playerweight,
//                player[0].playerName,
//                player[0].playerPosition,
//                player[0].playerDescription)
        tv_player_description.text = player[0].playerDescription
        Picasso.get().load(player[0].playerPhoto).into(img_player_photo)
        tv_player_weight.text = player[0].playerweight
        tv_player_height.text = player[0].playerHeight
        tv_player_position.text = player[0].playerPosition
    }
}
