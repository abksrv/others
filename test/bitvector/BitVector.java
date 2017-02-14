package bitvector;

public class BitVector {

	private static final int SHIFT = 6;
	private static final long MASK = 0x00000001;
	private static final int BITS_IN_LONG = Long.BYTES * 8;
	long[] bits = new long[0];
	long size = 0;

	public void set(long i) {
		int index = findIndexOfLong(i);
		resizeIfRequired(index);
		int bitPosition = getBitPosition(i, index);
		bits[index] |= setMask(bitPosition);
		if (i + 1 > size) {
			size = i + 1;
		}
	}

	private static long setMask(long bitPosition) {
		return (long) (MASK << (bitPosition + 1));
	}

	private static long resetMask(int bitPosition) {
		return ~((long) (MASK << (bitPosition + 1)));
	}

	private static int getBitPosition(long i, int index) {
		return (int) (i - BITS_IN_LONG * index);
	}

	private void resizeIfRequired(int index) {
		if (bits.length < index + 1) {
			long[] temp = new long[index + 1];
			System.arraycopy(bits, 0, temp, 0, bits.length);
			bits = temp;
		}
	}

	private static int findIndexOfLong(long i) {
		return (int) (i >> SHIFT);
	}

	public boolean isSet(long l) {
		if (l + 1 > size)
			throw new IllegalArgumentException();
		int index = findIndexOfLong(l);
		long mask = setMask(getBitPosition(l, index));
		return !((mask & bits[index]) == 0);
	}

	public void reset(long l) {
		if (l + 1 > size)
			throw new IllegalArgumentException();
		int index = findIndexOfLong(l);
		long mask = resetMask(getBitPosition(l, index));
		bits[index] &= mask;
	}
	
	public long length()
	{
		return size;
	}
}
