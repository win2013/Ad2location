package ad2location.admap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.google.android.maps.Overlay;
import com.google.android.maps.Point;
//import com.google.googlenav.Placemark;
//import com.google.googlenav.Search;

public class MyOverlay extends Overlay {
    ad2location mainMap;
    Service[] serData = new Service[20]; 
    private float[] mPts = new float[100];
    
    Paint paint1 = new Paint();
    Paint paint2 = new Paint();

    public MyOverlay(ad2location map) {
        mainMap = map;
        setServiceData();
       // paint2.setARGB(255, 255, 255, 255);
    }
 
    public void setServiceData(){
    	serData[0]=new Service();
    	serData[0].setLatitude((int) (26.244888*1000000) );
    	serData[0].setLongitude((int) (-80.253539*1000000) );
    	serData[0].setXmlContentMenu("<text> Free mexican taco w/ oder </text> <image>taco.gif</image>" +
    			"<button> order <url>http://android.ubiwireless.com/orderfood.php?taco</url> </button>");    	
    	serData[0].setType("Food");
    	serData[0].setName("Baja Fresh");
    	
    	serData[1] = new Service();
    	serData[1].setLatitude((int) (26.242232*1000000) );
    	serData[1].setLongitude((int) (-80.266714*1000000) );
    	serData[1].setXmlContentMenu("<text> Free mexican taco w/ oder </text> <app>hong.dk</app>");    	
    	serData[1].setType("Food");
    	serData[1].setName("Hong's Super Buffer ");    	
    }
    
    
    public void drawCircles(Canvas canvas, Paint paint){
    	//canvas.drawColor(Color.WHITE);        
        paint.setAntiAlias(true);

        // ROUND cap + width > 0 ... Squares (pretty slow)
        paint.setColor(Color.BLUE);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStrokeWidth(10);
        canvas.drawPoints(mPts, paint);

        // SQUARE cap + width > 0 ... Circles (very slow)
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(6);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoints(mPts, paint);

        // antialias + width == 0 ... blurry pixels (pretty fast)
        paint.setColor(Color.RED);
        paint.setStrokeWidth(0);
        canvas.drawPoints(mPts, paint);

        // no-antialias + width == 0 ... single pixels (very fast)
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(false);
        canvas.drawPoints(mPts, paint);
    }
    
    public void draw(Canvas canvas, PixelCalculator pixelCalculator, boolean b) {
        super.draw(canvas, pixelCalculator, b);
            for (int i = 0; i < 2; i++) {
                Service svc = serData[i];
                int[] screenCoords = new int[2];
                Point point = new Point(svc.getLatitude(),
                        				svc.getLongitude());
                pixelCalculator.getPointXY(point, screenCoords);
                mPts[(i << 1) + 0] = screenCoords[0];    // X
                mPts[(i << 1) + 1] = screenCoords[1];    // Y                
            }
            this.drawCircles(canvas, paint1);
    }
        
}
