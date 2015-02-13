package mt.ringbuffer;

public class GoodRingBuffer implements RingBuffer {
	
	private final int[] buffer;
    private long readCount;
    private long writeCount;
    
    private final Object guard = new Object();
    
    private boolean readersWaiting = false;
    private boolean writersWaiting = false;
    
    public GoodRingBuffer(int size) {
        buffer = new int[size];
    }
    
	@Override
	public int  get() throws InterruptedException {		
		synchronized(guard){
			while(readCount >= writeCount) {
				readersWaiting = true;
				guard.wait();
			}
				
			int readIndex = (int) (readCount % buffer.length);
	        readCount++;
	        if(writeCount < readCount + buffer.length && writersWaiting){
	        	readersWaiting = false;
				writersWaiting = false;
				guard.notifyAll();
			}
	        return buffer[readIndex];
		}
	}

	@Override
	public void put(int value) throws InterruptedException {
		synchronized(guard){
			while(writeCount >= readCount + buffer.length){
				writersWaiting = true;
				guard.wait();	
			}
				
			int writeIndex = (int) (writeCount % buffer.length);
			buffer[writeIndex] = value;
			writeCount++;
			
			if(readCount < writeCount && readersWaiting){
				readersWaiting = false;
				writersWaiting = false;
				guard.notify();
			}
		}
	}

}
