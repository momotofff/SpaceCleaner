package com.example.my_framework;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.io.IOException;
import java.io.InputStream;

public class GraphicsFW
{
    private AssetManager assetManager;
    private Bitmap frameBuffer;
    private Bitmap texture;
    private Canvas canvas;
    private Paint paint;

    public GraphicsFW(AssetManager assetManager, Bitmap frameBuffer)
    {
        this.assetManager = assetManager;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

    public void clearScene(int colorRGB)
    {
        canvas.drawRGB(colorRGB, colorRGB, colorRGB);
    }

    public void drawPixel(Point position, int color)
    {
        paint.setColor(color);
        canvas.drawPoint(position.x, position.y, paint);
    }

    public void drawLine(int startX, int startY, int stopX, int stopY, int color)
    {
        paint.setColor(color);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    public int measureText(StaticTextFW text)
    {
        paint.setTextSize(text.size);
        paint.setTypeface(text.font);
        return (int) paint.measureText(text.text);
    }

    public void drawText(StaticTextFW text)
    {
        paint.setColor(text.color);
        paint.setTextSize(text.size);
        paint.setTypeface(text.font);
        canvas.drawText(text.text, text.position.x, text.position.y, paint);
    }

    public void drawTexture(Bitmap bitmap, int x, int y)
    {
        canvas.drawBitmap(bitmap, x, y, null);
    }

    public int getWidthFrameBuffer()
    {
        return frameBuffer.getWidth();
    }

    public int getHeightFrameBuffer()
    {
        return frameBuffer.getHeight();
    }

    public Bitmap loadTexture(String fileName)
    {
        InputStream inputStream = null;

        try
        {
            inputStream = assetManager.open(fileName);
            texture = BitmapFactory.decodeStream(inputStream);

            if (texture == null)
            {
                throw new RuntimeException("File not found exception " + fileName);
            }
        }

        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        try
        {
            inputStream.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        return texture;
    }
}
