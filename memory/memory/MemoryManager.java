package memory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public class MemoryManager {

	private int myNumberOfPages;
	private int myPageSize; // In bytes
	private int myNumberOfFrames;
	private int[] myPageTable; // -1 if page is not in physical memory
	private byte[] myRAM; // physical memory RAM
	private RandomAccessFile myPageFile;
	private int myNextFreeFramePosition = 0;
	private int myNumberOfpageFaults = 0;
	private int myPageReplacementAlgorithm = 0;
	Queue <Integer> fifoQueue;
	LinkedHashSet<Integer> lruQueue;

	public MemoryManager(int numberOfPages, int pageSize, int numberOfFrames, String pageFile,
			int pageReplacementAlgorithm) {

		myNumberOfPages = numberOfPages;
		myPageSize = pageSize;
		myNumberOfFrames = numberOfFrames;
		myPageReplacementAlgorithm = pageReplacementAlgorithm;
		fifoQueue = new LinkedBlockingQueue<>(myNumberOfFrames);
		lruQueue = new LinkedHashSet<>(myNumberOfFrames);

		initPageTable();
		myRAM = new byte[myNumberOfFrames * myPageSize];

		try {

			myPageFile = new RandomAccessFile(pageFile, "r");

		} catch (FileNotFoundException ex) {
			System.out.println("Can't open page file: " + ex.getMessage());
		}
	}

	private void initPageTable() {
		myPageTable = new int[myNumberOfPages];
		for (int n = 0; n < myNumberOfPages; n++) {
			myPageTable[n] = -1;
		}
	}

	public byte readFromMemory(int logicalAddress) {
		int pageNumber = getPageNumber(logicalAddress);
		int offset = getPageOffset(logicalAddress);

		if (myPageTable[pageNumber] == -1) {
			pageFault(pageNumber);
		}else if (myPageReplacementAlgorithm == 2){
			lru_checker(pageNumber);
		}

		int frame = myPageTable[pageNumber];
		int physicalAddress = frame * myPageSize + offset;
		byte data = myRAM[physicalAddress];

		System.out.print("Virtual address: " + logicalAddress);
		System.out.print(" Physical address: " + physicalAddress);
		System.out.println(" Value: " + data);
		return data;
	}

	private int getPageNumber(int logicalAddress) {
		// Implement by student in task one
		return logicalAddress>>8;
	}

	private int getPageOffset(int logicalAddress) {
		// Implement by student in task one
		return (logicalAddress & 0b0000000011111111);
		//return (logicalAddress % myPageSize);
	}

	private void pageFault(int pageNumber) {
		if (myPageReplacementAlgorithm == Seminar3.NO_PAGE_REPLACEMENT)
			handlePageFault(pageNumber);

		if (myPageReplacementAlgorithm == Seminar3.FIFO_PAGE_REPLACEMENT)
			handlePageFaultFIFO(pageNumber);

		if (myPageReplacementAlgorithm == Seminar3.LRU_PAGE_REPLACEMENT)
			handlePageFaultLRU(pageNumber);

		readFromPageFileToMemory(pageNumber);
	}

	private void readFromPageFileToMemory(int pageNumber) {
		try {
			int frame = myPageTable[pageNumber];
			myPageFile.seek(pageNumber * myPageSize);
			for (int b = 0; b < myPageSize; b++)
				myRAM[frame * myPageSize + b] = myPageFile.readByte();
		} catch (IOException ex) {

		}
	}

	public int getNumberOfPageFaults() {
		return myNumberOfpageFaults;
	}

	private void handlePageFault(int pageNumber) {
		// Implement by student in task one
		// This is the simple case where we assume same size of physical and logical
		// memory
		// nextFreeFramePosition is used to point to next free frame position

		myPageTable[pageNumber] = myNextFreeFramePosition;
		myNextFreeFramePosition++;
		myNumberOfpageFaults++;

	}

	private void handlePageFaultFIFO(int pageNumber) {
		// Implement by student in task two
		// this solution allows different size of physical and logical memory
		// page replacement using FIFO
		// Note depending on your solution, you might need to change parts of the
		// supplied code, this is allowed.
		//fifoQueue.add(pageNumber);

		if (fifoQueue.size() == myNumberOfFrames){
			myPageTable[fifoQueue.remove()] = -1;
		}
		myNextFreeFramePosition = myNumberOfpageFaults % myNumberOfFrames;
		myNumberOfpageFaults++;
		fifoQueue.add(pageNumber);
		myPageTable[pageNumber] = myNextFreeFramePosition;


	}

	private void handlePageFaultLRU(int pageNumber) {
		// Implement by student in task three
		// this solution allows different size of physical and logical memory
		// page replacement using LRU
		// Note depending on your solution, you might need to change parts of the
		// supplied code, this is allowed.

		if (lruQueue.size() == myNumberOfFrames){
			int delete = lruQueue.stream().findFirst().get();
			myPageTable[delete] = -1;
			lruQueue.remove(delete);
		}
		myNextFreeFramePosition = myNumberOfpageFaults % myNumberOfFrames;
		myNumberOfpageFaults++;
		lruQueue.add(pageNumber);
		myPageTable[pageNumber] = myNextFreeFramePosition;
	}

	public void lru_checker(int page){
		//If no pagefault, check for the remove the page and add it again so it goes to the back of the queue
		lruQueue.remove(page);
		lruQueue.add(page);
	}
}
