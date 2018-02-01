package MinBinHeap;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size;
	private static final int arraySize = 10000; // Everything in the array will
												// initially
												// be null. This is ok! Just
												// build out
												// from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); // 0th will be unused for
		size = 0; // simplicity
		// of child/parent
		// computations...
		// the book/animation page
		// both do this.
	}

	@Override
	public EntryPair[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		bubbleUp(entry, size + 1);
		size++;
	}

	@Override
	public void delMin() {
		// TODO Auto-generated method stub
		if (size == 0) {
			return;
		} else if (size == 1) {
			array[1] = null;
			size--;
			return;
		} else {
			array[1] = array[size];
			array[size] = null;
			if (size > 1) {
				bubbleDown(1);
				size--;
			}
		}
	}

	@Override
	public EntryPair getMin() {
		// TODO Auto-generated method stub
		if (array[1] == null) {
			return null;
		} else {
			return array[1];
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		// TODO Auto-generated method stub
		if (entries.length == 0) {
			return;
		}
		for (int i = 0; i < entries.length; i++) {
			array[i + 1] = entries[i];
			size++;
		}
		for (int i = (int) Math.ceil(size / 2); i > 0; i--) {
			bubbleDown(i);
		}
	}

	public static void swap(EntryPair a, EntryPair b) {
		EntryPair c = a;
		a = b;
		b = c;
	}

	public static EntryPair MinOfThree(EntryPair a, EntryPair b, EntryPair c) {
		EntryPair min = a;
		if (min.getPriority() > b.priority) {
			min = b;
		}
		if (min.getPriority() > c.getPriority()) {
			min = c;
		}
		return min;
	}

	public void bubbleUp(EntryPair entry, int position) {
		int parentPosition = (int) Math.floor((position) / 2);

		array[position] = entry;

		if (array[parentPosition].getPriority() > entry.getPriority()) {
			EntryPair toBeInserted = array[position];
			array[position] = array[parentPosition];
			array[parentPosition] = toBeInserted;
			bubbleUp(entry, parentPosition);
		}
	}

	public void bubbleDown(int position) {
		EntryPair leftChild = array[2 * position];
		EntryPair rightChild = array[2 * position + 1];
		int minIndex;
		if (rightChild == null) {
			if (leftChild == null) {
				return;
			} else {
				minIndex = 2 * position;
			}
		} else {
			if (rightChild.getPriority() < leftChild.getPriority()) {
				minIndex = 2 * position + 1;
			} else {
				minIndex = 2 * position;
			}
		}
		if (array[position].getPriority() > array[minIndex].getPriority()) {
			EntryPair a = array[minIndex];
			array[minIndex] = array[position];
			array[position] = a;
			// swap(array[minIndex],array[position]);
			bubbleDown(minIndex);
		}
	}
}