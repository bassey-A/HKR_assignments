//*Best config so far*/
/* 67,0,2 speed 120, delay 1ms*/
#define F_CPU 16000000UL
#define BAUD 9600

#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>
#include <stdlib.h>
#include <math.h>
#include "includes/ADC.h"
#include "includes/USART.h"
#include "includes/motor.h"
#include "includes/timers.h"
#include "includes/ultrasound.h"
#include "includes/i2cmaster.h"
#include "includes/mpu6050.h"
#include "includes/mpu6050registers.h"
void system_init(void);
void Update_Vars(void);
void line_following(void);
void calculate_collision_vect();
#define L_SENSOR sensor_arr[0]
#define M_SENSOR sensor_arr[1]
#define R_SENSOR sensor_arr[2]
#define B_SENSOR sensor_arr[3]
#define AVERAGE 0
#define SINGLE 1
#define METHOD AVERAGE
uint8_t sensor_arr[SENSORS];
double ax,ay,az,gx,gy,gz;

uint8_t speed = 120;
uint8_t goal = 140;
double error = 0;
double last_error = 0;
double kp = 0;
double ki = 0;
double kd = 0;
double p_factor = 0;
double i_factor = 0;
double d_factor = 0;
double adjustment = 0;
int main(void)
{
	system_init();					
	ULTRASOUND_LEFT_READY = 0;
	uint8_t count = 0;
	while (1) 
    {	
		while(!START);
		while(count++ < 10){
			cli();
			mpu6050_getConvAccData(&ax,&ay,&az);
			sei();
			calculate_collision_vect();
			send_trig();
			obstacle_check();
			read_sensors(sensor_arr);
			#if METHOD == AVERAGE
			error = goal - ((L_SENSOR+M_SENSOR+R_SENSOR+B_SENSOR)/SENSORS);
			#endif
			#if METHOD == SINGLE
			error = goal - M_SENSOR;
			#endif
			p_factor = kp*error;
			d_factor = last_error - error;
			last_error = error;
			
			_delay_ms(1);
			count++;
		}
		i_factor += error;
		adjustment = p_factor + i_factor + d_factor;
		Update_Vars();		
		motor_left_speed(speed + adjustment);
		motor_right_speed(speed - adjustment);
		
		count = 0;
		
	}

}
void system_init(){
	START = 0;
	ADC_init();
	USART_init();
	motor_init();
	ultrasound_init();
	mpu6050_init();		
	sei();
}
void Update_Vars(){
	if(buf_index == SIZE_OF_PID_VARS){
		kp = (double)rec_buf[0]/(double)100;
		ki = (double)rec_buf[1]/(double)10000;
		kd = (double)rec_buf[2]/(double)10;
		speed =	rec_buf[3];
		goal = rec_buf[4];
		buf_index = 0;
	}
}
void calculate_collision_vect(){
	double len_accel_vect = sqrt((ax*ax)+(ay*ay));
	if(len_accel_vect > 0.9){
		USART_send_byte('Z');
	}
}