SIZE	.INT	10
TWO		.INT	2
ONE		.INT	1
INTSZ	.INT	4
D               .BYT     68
A               .BYT    65
G               .BYT    71
S               .BYT    83
ARR		.INT	10
		.INT	2
		.INT	3
		.INT	4
		.INT	15
		.INT	-6
		.INT	7
		.INT	8
		.INT	9
		.INT	10
i		.BYT	105
s		.BYT	115
e		.BYT	101
v		.BYT	118
n		.BYT	110
o		.BYT	111
d		.BYT	100
u		.BYT	117
m		.BYT	109
NL		.BYT	10
SPC		.BYT	32
Cnt		.INT	0
SUM		.INT	0
TEMP	.INT	0
RSLT	.INT	0
WHL	LDR		R5	Cnt		;---------------------------------->BEGIN PART ONE
	LDR		R4	SIZE
	CMP		R5	R4		;Compare I and Size
	BRZ		R5	ENDWL	;If they are the same, branch out of while
	LDR		R6	INTSZ	;Load size of int
	LDR		R5	Cnt		;Load I
	MUL		R5	R6		;Multiply I by size of int
	LDA		R6	ARR		;Load address of array into R6
	ADD		R6	R5		;&Arr+(I*sizeofint)
	LDR		R7	R6		;Moving value of ARR[i] to R7
	LDR		R1	SUM		;Loading SUM into R1
	ADD		R1	R7		;Adding SUM and ARR[i]
	STR		R1	SUM		;Storing SUM
	MOV		R1	R7		;Moving value of ARR[i] to R1
	LDR		R6	TWO		
	DIV		R1	R6
	MUL		R1	R6
	CMP		R1	R7
	BNZ		R1	ELSE
	MOV		R3	R7		;This will execute if ARR[i] is even
	TRP		1	
        LDB             R3      SPC
        TRP             3		;PRINTF("%d is even\n", arr[i])
	LDB		R3	i
	TRP		3
        LDB             R3      s
        TRP             3
        LDB             R3      SPC
        TRP             3
        LDB             R3      e
        TRP             3
	LDB             R3      v
	TRP             3
	LDB             R3      e
	TRP             3
 	LDB             R3      n
	TRP             3
	LDB		R3	NL
	TRP		3
        LDR             R7      ONE
        LDR             R5      Cnt
        ADD             R5      R7
        STR             R5      Cnt
	JMP		WHL
ELSE	MOV		R3	R7		;This will execute if ARR[i] is odd
	TRP		1			;PRINTF("%d is odd\n", arr[i])
	LDB		R3	SPC
	TRP		3
	LDB             R3      i
	TRP             3
	LDB             R3      s
	TRP             3
	LDB             R3      SPC
	TRP             3
	LDB             R3      o
	TRP             3
	LDB             R3      d
	TRP             3
	LDB             R3      d
	TRP             3
	LDB		R3	NL
	TRP		3
	LDR		R7	ONE
	LDR		R5	Cnt
	ADD     	R5	R7
	STR		R5	Cnt
	JMP 		WHL			;End while body
ENDWL   LDB             R3      S
	TRP             3
	LDB             R3      u
	TRP             3
	LDB     R3      m
	TRP     3
	LDB     R3      SPC
	TRP     3
	LDB     R3      i
	TRP     3
	LDB     R3      s
	TRP     3
	LDB     R3      SPC
	TRP     3
	LDR	R3	SUM
	TRP	1
	LDB     R3      NL
	TRP     3
	LDB	R3	D			;-------------------------->BEGIN PART TWO
	TRP	3
	LDB	R3	A
	TRP	3
	LDB	R3	G
	TRP	3
	LDB	R3	S
	TRP	3
	LDB	R3	SPC
	TRP	3
	LDR	R7	D
	MOV	R3	R7
	TRP	1
	LDB	R3	SPC
	TRP	3
	LDB	R4	D
	LDB	R5	G
	STB	R5	D
	STB	R4	G
        LDB     R3      D                       ;-------------------------->BEGIN PART TWO
        TRP     3
        LDB     R3      A
        TRP     3
        LDB     R3      G
        TRP     3
        LDB     R3      S
        TRP     3
        LDB     R3      SPC
        TRP     3
        LDR     R6      D
	MOV	R3	R6
	TRP	1
	LDB	R3	SPC
	TRP	3
	SUB	R7	R6
	MOV	R3	R7
	TRP	1
	LDB	R3	NL
	TRP	3
	TRP	0
