package com.example.wansa.gpmessage

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView

class antrenmanprogram : Fragment() {
    var mywebview: WebView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_antrenmanprogram, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var web = this.activity!!.findViewById<WebView>(R.id.webViewwww)

        //      val btn_close:Button= findViewById<Button>(R.id.btn_close)
        //     val btn_back:Button= findViewById<Button>(R.id.btn_back)


        var mWebSettings = web.getSettings()
        mWebSettings.setJavaScriptEnabled(true) // Done above
        mWebSettings.setDomStorageEnabled(true) // Try
        mWebSettings.setSupportZoom(false)
        mWebSettings.setAllowFileAccess(true)
        mWebSettings.setAllowContentAccess(true)

        web.loadData(     """
<iframe width="400" height="415" src="https://www.agirsaglam.com/vucut-gelistirme-antrenman-beslenme/" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            ""","text/html; charset=UTF-8", null   )

    }

}// Required empty public constructor


