package mt.ringbuffer;

public class GoodRingBuffer implements RingBuffer {
	
	private final int[] buffer;
    private long readCount;
    private long writeCount;
    
    public GoodRingBuffer(int size) {
        buffer = new int[size];
    }
    
	@Override
	public synchronized int  get() throws InterruptedException {		
			while(readCount >= writeCount) {
				wait();
			}
				
			int readIndex = (int) (readCount % buffer.length);
	        readCount++;
	        if(writeCount < readCount + buffer.length){
				notifyAll();
			}
	        return buffer[readIndex];
	}

	@Override
	public synchronized void put(int value) throws InterruptedException {	
			while(writeCount >= readCount + buffer.length){
				wait();
			}
				
			int writeIndex = (int) (writeCount % buffer.length);
	        buffer[writeIndex] = value;
	        writeCount++;
	        
	        if(readCount < writeCount){
				notifyAll();
			}
	}

}
