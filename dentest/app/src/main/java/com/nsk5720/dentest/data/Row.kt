package com.nsk5720.projectmobile.data

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Row(
    val BIZPLC_NM: String,
    val BSN_STATE_NM: String,
    val CLSBIZ_DE: String,
    val HOSPTLRM_CNT: Int,
    val LICENSG_DE: String,
    val LOCPLC_AR: Any,
    val MEDCARE_INST_ASORTMT_NM: Any,
    val MEDSTAF_CNT: Int,
    val REFINE_LOTNO_ADDR: String,
    val REFINE_ROADNM_ADDR: String,
    val REFINE_WGS84_LAT: String,
    val REFINE_WGS84_LOGT: String,
    val REFINE_ZIP_CD: String,
    val SICKBD_CNT: Int,
    val SIGUN_CD: String,
    val SIGUN_NM: String,
    val TOT_AR: Double,
    val TOT_PSN_CNT: Any,
    val TREAT_SBJECT_CONT: String
) : ClusterItem {
    // ctrl + i
    override fun getPosition(): LatLng {
        return LatLng(REFINE_WGS84_LAT.toDouble(),REFINE_WGS84_LOGT.toDouble())    // 개별 마커가 표시될 자표
    }

    override fun getTitle(): String? {
        return BIZPLC_NM   // 마커를 클릭했을 때 나타나는 타이틀
    }

    override fun getSnippet(): String? {
        return REFINE_LOTNO_ADDR    // 마커를 클릭했을 때 나타나는 서브타이틀
    }

}