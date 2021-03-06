// automatically generated by the FlatBuffers compiler, do not modify

package com.riiablo.net.packet.d2gs;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class CursorToStore extends Table {
  public static CursorToStore getRootAsCursorToStore(ByteBuffer _bb) { return getRootAsCursorToStore(_bb, new CursorToStore()); }
  public static CursorToStore getRootAsCursorToStore(ByteBuffer _bb, CursorToStore obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public CursorToStore __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public int storeLoc() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int x() { int o = __offset(6); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int y() { int o = __offset(8); return o != 0 ? bb.getInt(o + bb_pos) : 0; }

  public static int createCursorToStore(FlatBufferBuilder builder,
      int storeLoc,
      int x,
      int y) {
    builder.startObject(3);
    CursorToStore.addY(builder, y);
    CursorToStore.addX(builder, x);
    CursorToStore.addStoreLoc(builder, storeLoc);
    return CursorToStore.endCursorToStore(builder);
  }

  public static void startCursorToStore(FlatBufferBuilder builder) { builder.startObject(3); }
  public static void addStoreLoc(FlatBufferBuilder builder, int storeLoc) { builder.addInt(0, storeLoc, 0); }
  public static void addX(FlatBufferBuilder builder, int x) { builder.addInt(1, x, 0); }
  public static void addY(FlatBufferBuilder builder, int y) { builder.addInt(2, y, 0); }
  public static int endCursorToStore(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

