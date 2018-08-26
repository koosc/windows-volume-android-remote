package ckoos.windows_volume_remote
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ckoos.windows_volume_remote.sync.ApplicationSync
import ckoos.windows_volume_remote.sync.VolumeApplication
import kotlinx.android.synthetic.main.application_control_fragment.*

class ApplicationControlFragment : Fragment() {

    //2
    companion object {

        fun newInstance(): ApplicationControlFragment {
            return ApplicationControlFragment()
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view: View = inflater.inflate(R.layout.application_control_fragment, container, false)







        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var viewAdapter: ApplicationAdapter
        lateinit var viewManager: RecyclerView.LayoutManager

        viewAdapter = ApplicationAdapter(ArrayList<VolumeApplication>())

        val appConnection = ApplicationSync("http://192.168.1.20", viewAdapter)
        var applicationData: ArrayList<VolumeApplication> = appConnection.getApplicationList()


        var testVol: VolumeApplication = VolumeApplication()



        viewManager = LinearLayoutManager(this.context)
//        viewAdapter = ApplicationAdapter(applicationData)
        applicationRecycler.layoutManager = viewManager
        viewAdapter.notifyDataSetChanged()

        applicationRecycler.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

    }
}