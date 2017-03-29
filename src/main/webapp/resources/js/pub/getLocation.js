/**
 * Created by 林强 on 2017-02-26.
 */

function getLocal(showPosition)
{
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(showPosition,showError);


}
function showError(error)
{
    alert(error.jf);
}
