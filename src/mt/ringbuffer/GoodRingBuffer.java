package mt.ringbuffer;

public class GoodRingBuffer implements RingBuffer {
	
	private final int[] buffer;
    private volatile long readCount;
    private volatile long writeCount;
    
    private static final Object guard = new Object();
    
    public GoodRingBuffer(int size) {
        buffer = new int[size];
    }
    
	@Override
	public int get() throws InterruptedException {
		synchronized(guard){
			if(readCount >= writeCount) {
				guard.wait();
			}
				
			int readIndex = (int) (readCount % buffer.length);
	        readCount++;
	        if(writeCount < readCount + buffer.length){
				guard.notifyAll();
			}
	        return buffer[readIndex];
		}
	}

	@Override
	public void put(int value) throws InterruptedException {
		synchronized(guard){
			if(writeCount >= readCount + buffer.length){
				guard.wait();
			}
				
			int writeIndex = (int) (writeCount % buffer.length);
	        buffer[writeIndex] = value;
	        writeCount++;
	        
	        if(readCount < writeCount){
				guard.notifyAll();
			}
		}
	}

}
