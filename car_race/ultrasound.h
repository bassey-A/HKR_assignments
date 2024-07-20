#include <avr/io.h>

#ifndef ULTRASOUND_H_
#define ULTRASOUND_H_
#define ULTRASOUND_SENSORS 2
#define LEFT_ULTRA 0
#define RIGHT_ULTRA 1
#define MIN_DISTANCE_OBSTACLE 120
volatile uint32_t distance[ULTRASOUND_SENSORS];
void ultrasound_init(void);
void EXT_init(void);
void send_trig(void);
void obstacle_check(void);
#endif /* ULTRASOUND_H_ */