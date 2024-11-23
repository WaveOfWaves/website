/** @type {import('tailwindcss').Config} */
module.exports = {
    content: {
        files: [
            './out/**/*.html',
        ]
    },
    theme: {
        fontFamily: {
            'mono': ['JetBrains Mono', 'monospace'],
        },
        extend: {
            keyframes: {
                heartBeat: {
                    '0%': { transform: 'scale(1);' },
                    '14%': { transform: 'scale(1.2);' },
                    '28%': { transform: 'scale(1);' },
                    '42%': { transform: 'scale(1.2);' },
                    '70%': { transform: 'scale(1);' },
                },
            },
            animation: {
                heartBeat: 'heartBeat 1s infinite',
            },
        },
    },
    plugins: [
        require('@tailwindcss/typography'),
    ],
}
