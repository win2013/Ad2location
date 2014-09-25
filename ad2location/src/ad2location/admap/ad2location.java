package ad2location.admap;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.*;
import android.view.Menu;
import android.view.Menu.Item;
import android.view.KeyEvent;

public class ad2location extends MapActivity {
    private MapView mMapView;
    public  Point currentPoint; 
    
    public static final int ZOOM_IN_INDEX = Menu.FIRST;
    public static final int ZOOM_OUT_INDEX = Menu.FIRST + 1;
    public static final int SATELLITE_INDEX = Menu.FIRST + 2;
    public static final int TRAFFIC_INDEX = Menu.FIRST + 3;
    public static final int ADS_MENU = Menu.FIRST + 4;
    public static final int CENTER_GPS_INDEX = Menu.FIRST + 5;
    public static final int TRACK_GPS_INDEX = Menu.FIRST + 6;
    public static final int SAVE_INDEX = Menu.FIRST + 7;
    public static final int EDIT_INDEX = Menu.FIRST + 8;
    
    public static final long GPS_OFFSET = 10000; 
    
        @Override
    public void onCreate(Bundle icicle) {
        	super.onCreate(icicle);
        
        	mMapView = new MapView(this);      
        	Point p = new Point((int) (26.250000 * 1000000), (int)
                				  (-80.260000 * 1000000));
        	currentPoint = p;
        
        	MapController mc = mMapView.getController();
        	mc.animateTo(p);
        	mc.zoomTo(14);
        	setContentView(mMapView);
        	mMapView.createOverlayController().add(new MyOverlay(this), true);         
        }
   
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
    		super.onCreateOptionsMenu(menu);
    	
    		menu.add(0, ZOOM_IN_INDEX, R.string.zoom_in);
    		menu.add(0, ZOOM_OUT_INDEX, R.string.zoom_out);
    		menu.add(0, SATELLITE_INDEX, R.string.satellite);
    		menu.add(0, TRAFFIC_INDEX, R.string.traffic);
    		//menu.add(0, FIND_INDEX, R.string.find);
    		menu.add(0, CENTER_GPS_INDEX, R.string.center_gps);
    		menu.add(0, TRACK_GPS_INDEX, R.string.track_gps);
    		menu.add(0, SAVE_INDEX, R.string.save);
    		menu.add(0, EDIT_INDEX, R.string.edit);
    		menu.add(0, ADS_MENU, R.string.adsmenu);
    		return super.onCreateOptionsMenu(menu);
        }
      
        public boolean onOptionsItemSelected(Item item) {
    		switch(item.getId()) {
    		case ZOOM_IN_INDEX:
    		    return mapZoom(mMapView.getZoomLevel()+1);
    		case ZOOM_OUT_INDEX:
    			return mapZoom(mMapView.getZoomLevel()-1);
    		case ADS_MENU:
    			drawSquare();
    		/* case SATELLITE_INDEX:
    			return performToggleSatellite();
    		case TRAFFIC_INDEX:
    			return performToggleTraffic();
    		case FIND_INDEX:
    			return performFindLocation();
    		case CENTER_GPS_INDEX:
    			return performCenterOnGPS();
    		case TRACK_GPS_INDEX:
    			return performTrackGPS();
    		case SAVE_INDEX:
    			return performCreateBookmark();
    		case EDIT_INDEX:
    			return performEditBookmarks();
    		*/
    		}
    		return super.onOptionsItemSelected(item);
        }

        public boolean onKeyDown(int keyCode, KeyEvent event) {
        	mapMove(keyCode);
        	if (keyCode == KeyEvent.KEYCODE_I) {
                return mapZoom(mMapView.getZoomLevel()+1);
            } else if (keyCode == KeyEvent.KEYCODE_O) {
            	return mapZoom(mMapView.getZoomLevel()-1);
            } /* else if (keyCode == KeyEvent.KEYCODE_S) {
                return performToggleSatellite();
            } else if (keyCode == KeyEvent.KEYCODE_T) {
                return performToggleTraffic();
            } else if (keyCode == KeyEvent.KEYCODE_F) {
                return performFindLocation();
            } else if (keyCode == KeyEvent.KEYCODE_G) {
                return performCenterOnGPS();
            } else if (keyCode == KeyEvent.KEYCODE_X) {
                return performTrackGPS();
            } else if (keyCode == KeyEvent.KEYCODE_C) {
                return performCreateBookmark();
            } else if (keyCode == KeyEvent.KEYCODE_E) {
                return performEditBookmarks();
            } else if (keyCode == KeyEvent.KEYCODE_P) {
                return performFindPizza();
            } else if (keyCode >= KeyEvent.KEYCODE_1 && keyCode <= KeyEvent.KEYCODE_9) {
                int item = keyCode - KeyEvent.KEYCODE_1;
                if (mSearch != null && mSearch.numPlacemarks() > item) {
                    notifyUser(999, mSearch.getPlacemark(item).getDetailsDescriptor());
                    goTo(item);
                }
            }*/
            return false;
        } 
        
        public boolean mapZoom(int level){        	
            mMapView.getController().zoomTo(level);
            return true;
        }
        
        public boolean mapMove(int keyCode) {
        	long latitude = currentPoint.getLatitudeE6();
        	long longitude = currentPoint.getLongitudeE6(); 
        	switch (keyCode) {
        		case KeyEvent.KEYCODE_DPAD_DOWN:  longitude = longitude + GPS_OFFSET/4;	
        										  break;
        		case KeyEvent.KEYCODE_DPAD_UP: 	  longitude = longitude - GPS_OFFSET/4;
        										  break;
        		case KeyEvent.KEYCODE_DPAD_LEFT:  latitude = latitude + GPS_OFFSET/4;
        										  break;	
        		case KeyEvent.KEYCODE_DPAD_RIGHT: latitude = latitude - GPS_OFFSET/4;
        										   break;
        		default:
        			return false;
        		}
        	
        	MapController mc = mMapView.getController();        	        	       	
        	currentPoint = new Point((int)latitude, (int)longitude );
        	mc.animateTo(currentPoint);
        	return true;
        }
        
        public void drawSquare(){
        	setContentView(new MyDrawRectangle(this));
        	setContentView(mMapView);
        }
                
}
        	
 