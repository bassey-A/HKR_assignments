#ifndef TIMERS_H_
#define TIMERS_H_

#include <avr/io.h>
#define MOTOR_PWM 0
#define ULTRASOUND 1
#define IR 2
#define ACCELEROMETER 3
volatile uint8_t ULTRASOUND_LEFT_READY;
volatile uint8_t ULTRASOUND_RIGHT_READY;
void TIMER_init(uint8_t);
#endif /* TIMERS_H_ */