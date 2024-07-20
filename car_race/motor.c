#include "motor.h"
#include "timers.h"
#define L_FWD (1<<PORTD2)
#define L_BACK (1<<PORTD4)
#define L_PWM (1<<PORTD5)
#define R_PWM (1<<PORTD6)
#define R_BACK (1<<PORTD7)
#define R_FWD (1<<PORTB0)
void motor_init(){
	TIMER_init(MOTOR_PWM);
	DDRD |= (1<<PIND6) | (1<<PIND5);
}
void motor_left_speed(uint8_t pow){
	OCR0B = pow;
}
void motor_right_speed(uint8_t pow){
	OCR0A = pow;
}
void motor_speed(uint8_t left, uint8_t right){
	OCR0A = right;	
	OCR0B = left;
}
void motor_left_direction(uint8_t direction){
	
	switch(direction){
		case FORWARD:
			PORTD &= ~L_BACK;
			PORTD |= L_FWD;
			break;
		case BACKWARD:
			PORTD &= ~L_FWD;
			PORTD |= L_BACK;
			break;
		case STOP:
			PORTD &= ~L_BACK;
			PORTD &= ~L_FWD;
		default:
			break;
	}
}
void motor_right_direction(uint8_t direction){
	switch(direction){
		case FORWARD:
			PORTD &= ~R_BACK;
			PORTB |= R_FWD;
			break;
		case BACKWARD:
			PORTD &= ~R_FWD;
			PORTD |= R_BACK;
			break;
		case STOP:
			PORTD &= ~R_BACK;
			PORTB &= ~R_FWD;
			default:
		break;
	}
}
void motor_direction(uint8_t direction){
	switch(direction){
		case FORWARD:
			
			PORTD &= ~(1<<PORTD4);
			PORTD &= ~(1<<PORTD7);
			PORTD |= (1<<PORTD2);
			PORTB |= (1<<PORTB0);
			/*
			motor_left_direction(FORWARD);
			motor_right_direction(FORWARD);
			*/
			break;
		case BACKWARD:
			motor_left_direction(BACKWARD);
			motor_right_direction(BACKWARD);
			break;
		case LEFT_BACKWARD:
			motor_left_direction(BACKWARD);
			motor_right_direction(FORWARD);	
			break;
		case RIGHT_BACKWARD:
			motor_left_direction(FORWARD);
			motor_right_direction(BACKWARD);		
			break;
		case STOP:
			PORTD &= ~(1<<PORTD4);
			PORTD &= ~(1<<PORTD7);
			PORTD &= ~(1<<PORTD2);
			PORTB &= ~(1<<PORTB0);		
		default:
			break;
	}
}