#include "Accelerometer.h"
#define F_CPU 16000000UL
#include <avr/interrupt.h>
#include <util/delay.h>
#include "USART.h"
#include "motor.h"
#include "timers.h"
#include "Accelerometer.h"
#include "i2cmaster.h"
#include "mpu6050.h"
#include "mpu6050registers.h"
int16_t ax,ay,az;

ISR(TIMER2_OVF_vect){
	mpu6050_getRawAccData(&ax,&ay,&az);
}
void accelerometer_init(){
	accel_lower = -2000;
	accel_upper = 2000;
	mpu6050_init();
	TIMER_init(ACCELEROMETER);
}
void calculate_collision_vect(){
	double axg = (double)(ax)/16384;
	double ayg = (double)(ay)/16384;
	double collision = sqrt( (axg*axg)/(ayg*ayg) );
}
void gather_accel_data(){
	/*First test*/
	/*Try threshold 2k and -2k*/
	/*
	while(!START);
	mpu6050_getRawData(&ax,&ay,&az,&gx,&gy,&gz);
	USART_send_int_as_string(ax);
	USART_send_byte(' ');
	USART_send_int_as_string(ay);
	USART_send_byte(' ');
	USART_send_int_as_string(az);
	USART_send_byte(' ');
	USART_send_int_as_string(gx);
	USART_send_byte(' ');
	USART_send_int_as_string(gy);
	USART_send_byte(' ');
	USART_send_int_as_string(gz);
	USART_send_byte('\n');
	*/
}
