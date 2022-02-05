ublic enum Direction{
	UP,
	DOWN,
	STOPPED, // some solution does consider this and timeout for waiting passengers to come inside
	IDLE
}

public class Request{
	Type type;
	int floor;
	public Request(int floor){
		this.floor = floor;
	}
}

public class InternalRequest extends Request{
	public InternalRequest(int floor){
		super(floor);
		// type = Type.INSIDE;
	}
}

public class ExternalRequest extends Request{
	Direction direction;
	public ExternalRequest(int floor, Direction direction){
		super(floor);
		// type = Type.OUTSIDE; 
	}
}

public class Button{
	int floor;
	public Button(int floor){
		this.floor = floor;
	}
}

public class Elevator{
	public Status status;
	public int currentFloor;
	public boolean[] upStops;
	public boolean[] downStops;
	public int upStopsCount;
	public int downStopsCount;
	public Button[] buttons; // or List<Button> buttons
	public Controller controller;
	
	pubic Elevator(){
		direction = Direction.IDLE;
		currentFloor = 1;
		upStops = new boolean[n];
		downStops = new boolean[n];
		controller = new Controller(); 
		// deprecated
		// upRequests = new PriorityQueue<>((a, b) -> Integer.compare(a.floor, b.floor));
		// downRequests = new PriorityQueue<>((a, b) -> Integer.compare(b.floor, a.floor));
	}
	
	public void processUpRequest(){
		if(this.direction == Direction.IDLE) return;
		for(int i = this.currentFloor + 1; i < upStops.length; i++){
			if(upStops[i]){
				this.controller.goUp(i);
				openGate();
				closeGate();
			}
		}
		// all the left requests will be down requests
		// if none, the elevator is in idle state
		if(downRequestsCount == 0){
			this.direction = Direction.IDLE;
		}else{
			this.direction = Direction.DOWN;
		}
	}
	
	public void processDownRequest(){
		if(this.direction == Direction.IDLE) return;
		for(int i = this.currentFloor - 1; i >= 0; i--){
			if(downStops[i]){
				this.controller.goDown(i);
				openGate();
				closeGate();
			}
		}
		if(upRequestsCount == 0){
			this.direction = Direction.IDLE;
		}else{
			this.direction = Direction.UP;
		}
	}
	
	public void processButton(Button button){
		addRequest(new InternalRequest(button.floor));
	}
	
	// we update elevator's status when add request, or we can modify to add button design here, replace parameter from "request" to "button"
	public void addRequest(Request request){
		if(this.direction = Direction.UP){
			if(request.floor > this.currentFloor) {
				upStops[request.floor] = true;
				upStopsCount++;
			}
			else{
				downStops[request.floor] = true;
				downStopsCount++;
			}
		}else if(this.direction = Direction.DOWN){
			if(request.floor < this.currentFloor) {
				downStops[request.floor] = true;
				downStopsCount++;
			else {
				upStops[request.floor] = true;
				upStopsCount++;
			}
		} else{
			// if it is IDLE, then direction depends on current level and target level
			if(request.floor > this.currentFloor){
				upStops[request.floor] = true;
				upStopsCount++;
				this.direction = Direction.UP;
			}else{
				downStops[request.floor] = true;
				downStopsCount++;
				this.direction = Direction.DOWN;
			}
		}
	}
	
	public void openGate(Direction direction){
		this.controller.openGate();
		this.currentFloor = i;
		if(direction = Direction.UP){
		this.currentFloor = i;
			upStops[i] = false;
			upRequestsCount--;
		}else{
			downStops[i] = false;
			downRequestsCount--;
		}
		// wait for passengers to get in or out, e.g.
		this.controller.sleep(10); // sleep for 10 seconds
	}
	
	public void closeGate(){
		this.controller.closeGate();
	}
	
	public void start(){
		while(true){
			processUpRequests();
			processDownRequest();
		}
	}
}