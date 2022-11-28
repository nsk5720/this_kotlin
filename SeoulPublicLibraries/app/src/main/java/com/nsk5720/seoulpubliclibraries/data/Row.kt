package data

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Row(
    val ADRES: String,
    val CODE_VALUE: String,
    val FDRM_CLOSE_DATE: String,
    val GU_CODE: String,
    val HMPG_URL: String,
    val LBRRY_NAME: String,
    val LBRRY_SEQ_NO: String,
    val LBRRY_SE_NAME: String,
    val OP_TIME: String,
    val TEL_NO: String,
    val XCNTS: String,
    val YDNTS: String
) : ClusterItem {
    // ctrl + i
    override fun getPosition(): LatLng {
        return LatLng(XCNTS.toDouble(),YDNTS.toDouble())    // 개별 마커가 표시될 자표
    }

    override fun getTitle(): String? {
        return LBRRY_NAME   // 마커를 클릭했을 때 나타나는 타이틀
    }

    override fun getSnippet(): String? {
        return ADRES    // 마커를 클릭했을 때 나타나는 서브타이틀
    }

    // id에 해당하는 유일한 값을 Int로 변환 (Ctrl + o)
    override fun hashCode(): Int {
        return LBRRY_SEQ_NO.toInt()
    }
}