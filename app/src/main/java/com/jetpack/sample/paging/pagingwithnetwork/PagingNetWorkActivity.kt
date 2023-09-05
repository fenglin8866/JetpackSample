package com.jetpack.sample.paging.pagingwithnetwork

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jetpack.pagingwithnetwork.reddit.repository.RedditPostRepository
import com.jetpack.sample.databinding.ActivityPagingNetworkBinding
import com.jetpack.sample.paging.pagingwithnetwork.reddit.ui.RedditActivity

class PagingNetWorkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPagingNetworkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingNetworkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.withDatabase.setOnClickListener {
            show(RedditPostRepository.Type.DB)
        }
        binding.networkOnly.setOnClickListener {
            show(RedditPostRepository.Type.IN_MEMORY_BY_ITEM)
        }
        binding.networkOnlyWithPageKeys.setOnClickListener {
            show(RedditPostRepository.Type.IN_MEMORY_BY_PAGE)
        }
    }

    private fun show(type: RedditPostRepository.Type) {
        val intent = RedditActivity.intentFor(this, type)
        startActivity(intent)
    }
}