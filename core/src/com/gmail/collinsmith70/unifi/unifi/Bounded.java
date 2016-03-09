package com.gmail.collinsmith70.unifi.unifi;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gmail.collinsmith70.unifi.unifi.math.Boundary;
import com.gmail.collinsmith70.unifi.unifi.math.Point2D;

public interface Bounded extends Positioned, Sized {

  int getBottom();

  void setBottom(int bottom);

  int getLeft();

  void setLeft(int left);

  int getRight();

  void setRight(int right);

  int getTop();

  void setTop(int top);

  @NonNull
  Boundary getBoundary();

  @NonNull
  Boundary getBoundary(@Nullable Boundary dst);

  void setBoundary(int left, int top, int right, int bottom);

  void setBoundary(@NonNull Boundary src);

  int getX();

  void setX(int x);

  int getY();

  void setY(int y);

  @IntRange(from = 0, to = Integer.MAX_VALUE)
  int getWidth();

  @IntRange(from = 0, to = Integer.MAX_VALUE)
  int getHeight();

  boolean contains(int x, int y);

  boolean contains(Point2D point);

}
