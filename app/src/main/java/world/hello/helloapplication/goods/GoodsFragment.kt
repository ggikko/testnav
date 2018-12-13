package world.hello.helloapplication.goods


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import world.hello.helloapplication.R
import world.hello.helloapplication.home.HomeViewModel

class GoodsFragment : Fragment() {

    lateinit var goodViewModel: GoodViewModel
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_goods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goodViewModel = ViewModelProviders.of(this).get(GoodViewModel::class.java)

        activity?.let {
            homeViewModel = ViewModelProviders.of(it).get(HomeViewModel::class.java)
        }
    }


}
