
package jendgamexbox;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Slam
 */
public class TGAHeader {

    private byte idLen;
    private byte colorMapType;
    private byte imgType;
    private short colorMapOfs;
    private short numColorMap;
    private byte colorMapDepth;
    private short xOffset;
    private short yOffset;
    private short width;
    private short height;
    private byte imgDepth;
    private byte imgDescriptor;

    public TGAHeader() {
        // Default constructor
    }

    public boolean isTopToBottom() {
        return (imgDescriptor & 0x20) == 0x20;
    }

    public boolean isLeftToRight() {
        return (imgDescriptor & 0x10) != 0x10;
    }

    public boolean isCompressed() {
        return (imgType & 0x08) != 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TGAHeader - \n");
        sb.append("            id_len: 0x").append(String.format("%02X", idLen)).append("\n")
                .append("      color_map_type: 0x").append(String.format("%02X", colorMapType)).append("\n")
                .append("           img_type: 0x").append(String.format("%02X", imgType)).append("\n")
                .append("     color_map_ofs: 0x").append(String.format("%02X", colorMapOfs)).append("\n")
                .append("      num_color_map: 0x").append(String.format("%02X", numColorMap)).append("\n")
                .append("    color_map_depth: 0x").append(String.format("%02X", colorMapDepth)).append("\n")
                .append("           x_offset: 0x").append(String.format("%02X", xOffset)).append("\n")
                .append("           y_offset: 0x").append(String.format("%02X", yOffset)).append("\n")
                .append("             width: 0x").append(String.format("%02X", width)).append("\n")
                .append("            height: 0x").append(String.format("%02X", height)).append("\n")
                .append("          img_depth: 0x").append(String.format("%02X", imgDepth)).append("\n")
                .append("      img_descriptor: 0x").append(String.format("%02X", imgDescriptor)).append("\n")
                .append("      |- top_to_bottom: ").append(isTopToBottom()).append("\n")
                .append("      |- left_to_right: ").append(isLeftToRight()).append("\n")
                .append("      |--- compressed: ").append(isCompressed()).append("\n");
        return sb.toString();
    }

    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(18);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(idLen);
        buffer.put(colorMapType);
        buffer.put(imgType);
        buffer.putShort(colorMapOfs);
        buffer.putShort(numColorMap);
        buffer.put(colorMapDepth);
        buffer.putShort(xOffset);
        buffer.putShort(yOffset);
        buffer.putShort(width);
        buffer.putShort(height);
        buffer.put(imgDepth);
        buffer.put(imgDescriptor);
        return buffer.array();
    }

    // Getters and Setters for all fields
    void setImgType(byte b) {
        this.imgType = b;
    }

    void setWidth(short s) {
        this.width = s;
    }

    void setHeight(short s) {
        this.height = s;
    }

    void setImgDepth(byte b) {
        this.imgDepth = b;
    }

    void setImgDescriptor(byte b) {
        this.imgDescriptor = b;
    }

    int getImgType() {
        return this.imgType;
    }
}
