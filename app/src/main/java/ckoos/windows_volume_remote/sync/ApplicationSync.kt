package ckoos.windows_volume_remote.sync
import android.util.Log
import ckoos.windows_volume_remote.ApplicationAdapter
import com.github.kittinunf.fuel.Fuel
import org.json.JSONArray
import org.json.JSONObject

class ApplicationSync (hostLocation: String, adapter: ApplicationAdapter) {

    val hostConnection: String
    val hostPort: Int = 12345
    lateinit var adapter: ApplicationAdapter

    var applications: JSONArray = JSONArray()
    init {
        this.hostConnection = hostLocation + ":" + hostPort.toString()
        this.adapter = adapter
        getApplications()
    }


    fun getApplicationList() : ArrayList<VolumeApplication>{
        var applicationList = ArrayList<VolumeApplication>()
        Log.d("applicationsync", "apps")
        Log.d("applicationsync", applications.toString())
        for (i in 0..(applications.length() - 1)) {
//            var app = VolumeApplication()
            var app = applications.get(i)
            applicationList.add(VolumeApplication())
        }
        Log.d("applicationsync", "list")
        Log.d("applicationsync", applicationList.toString())
        Log.d("applicationsync", applications.toString())
        return applicationList
    }

    fun setVolume(application: Int, desiredVolume: Int) {

        val setVolumeBody = JSONObject()
        setVolumeBody.put("application", application)

        if (hostConnection == "") {
            Log.e("applicationsync", "Host has to be registered before calls can be made")
        }
        else {
            Fuel.post(hostConnection).body(setVolumeBody.toString())
        }
    }

    private fun getApplications() {
        val getApplicationEndpoint = "/getApplications"

        Fuel.get(hostConnection + getApplicationEndpoint).responseString {request, response, result ->
            Log.d("applicationsync", result.get())
            val applicationsJson: JSONArray = JSONArray(result.get())
            this.applications = applicationsJson
            adapter.addItem(this.getApplicationList().get(0))
            adapter.notifyDataSetChanged()

//            set(applications) { return applicationsJson }
            Log.d("applicationsync", "applications")
            Log.d("applicationsync", this.applications.toString())
//            Log.d("applicationsync", applicationsJson.getJSONObject(0).getString("name"))
            //do something with response
//            result.fold({ d ->
//                Log.d("applicationsync", d.toString())
//                //do something with data
//            }, { err ->
//                //do something with error
//                null
//            })
        }
        Log.d("applicationsync", "Got applications")
    }

}
